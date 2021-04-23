package factory;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.DefaultCreator;
import play.Logger;

import java.io.IOException;

/**
 * Created by bmougel
 */
public class MongoManager {
    private static Logger.ALogger logger = play.Logger.of(MongoManager.class.getName());

    private final MongoClientURI mongoURI;
    private final MongoClient mongo;
    private final Morphia morphia;

    // helpfull to restrict upsertable classes search
    private final static String PACKAGE_ROOT_NAME = "app";

    /**
     * Constructeur privé
     */
    private MongoManager() {
        Config conf = ConfigFactory.load();

        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();

        //build the connection options
        builder.maxConnectionIdleTime(conf.getInt("mongo.maxConnectionIdleTime"));//set the max wait time in (ms)
        builder.connectionsPerHost(conf.getInt("mongo.connectionsPerHost"));

        this.mongoURI = new MongoClientURI(conf.getString("mongo.uri"), builder);

        this.mongo = new MongoClient(this.mongoURI);
        this.morphia = new Morphia();
        this.morphia.mapPackage(PACKAGE_ROOT_NAME);

        addAllClassesMongo();
    }


    /**
     * Holder
     */
    private static class MongoManagerHolder {
        /**
         * Instance unique non préinitialisée
         */
        private static final MongoManager INSTANCE = new MongoManager();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static MongoManager getInstance() {
        return MongoManagerHolder.INSTANCE;
    }

    public MongoClient getMongo() {
        return this.mongo;
    }

    public Morphia getMorphia() {
        return this.morphia;
    }

    public String getDataBase() {
        return this.mongoURI.getDatabase();
    }

    public Datastore getDatastore() {
        Datastore datastore = this.morphia.createDatastore(this.getMongo(),
                this.getDataBase());
        try {
            datastore.ensureIndexes();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return datastore;
    }


    private void addAllClassesMongo() {
        ClassPath classPath = null;
        try {
            classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        ImmutableSet<ClassPath.ClassInfo> classes = classPath.getTopLevelClassesRecursive(PACKAGE_ROOT_NAME);


        for (ClassPath.ClassInfo classInfo : classes) {
            String[] splitName = classInfo.getName().split("\\.");
            if (splitName.length == 0) {
                continue;
            }

            ClassLoader tccl = Thread.currentThread().getContextClassLoader();

            // Configuring class loader
            this.morphia.getMapper().getOptions().setObjectFactory(
                new DefaultCreator() {
                    @Override
                    protected ClassLoader getClassLoaderForClass() {
                        return tccl;
                    }
                });


            if (classInfo.getName().contains("Actor")) {
                continue;
            }

            Class<?> entity = null;
            try {
                logger.trace(classInfo.getName());
                entity = Class.forName(classInfo.getName(), true, tccl);
            } catch (Exception e) {
                logger.warn(e.getMessage());
                continue;
            }

            //this.entity = Class.forName(classInfo.getName());
            if (entity.getAnnotations() != null &&
                    entity.getAnnotation(org.mongodb.morphia.annotations.Entity.class) != null) {
                logger.debug("morphia map : " + classInfo.getName());
                this.morphia.map(entity);
            }
        }

    }
}
