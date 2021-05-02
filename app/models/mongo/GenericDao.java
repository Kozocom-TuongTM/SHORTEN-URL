package models.mongo;

import factory.MongoManager;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import play.Logger;

public abstract class GenericDao<T extends Common> extends BasicDAO<T, ObjectId> {
    private static Logger.ALogger logger = Logger.of(GenericDao.class);

    private final Class<T> myClass;

    protected Datastore datastore;

    protected GenericDao(final Class<T> myClass) {
        super(myClass, MongoManager.getInstance().getDatastore());

        this.myClass = myClass;
        this.datastore = MongoManager.getInstance().getDatastore();
    }

    public Key<T> save(T entity) {
        return this.getDatastore().save(entity);
    }
}
