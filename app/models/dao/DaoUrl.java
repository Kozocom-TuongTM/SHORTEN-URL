package models.dao;

import models.Url;
import com.mongodb.AggregationOptions;
import models.mongo.GenericDao;
import org.bson.types.ObjectId;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import static org.mongodb.morphia.aggregation.Group.*;
import static org.mongodb.morphia.aggregation.Projection.projection;

@Singleton
public class DaoUrl extends GenericDao<Url>{

    private static class SingletonHolder {
        private final static DaoUrl instance = new DaoUrl();
    }

    public static DaoUrl getInstance() {
        return SingletonHolder.instance;
    }

    protected DaoUrl() {
        super(Url.class);
    }
    
    public Url findByShortUrl(String shortUrl){
        org.mongodb.morphia.query.Query<Url> mongoQuery = this.getDatastore()
           .createQuery(Url.class);

        mongoQuery.field("short_url").equal(shortUrl);
        
        return mongoQuery.get();
    }
    public Url createUrl(String long_url,String short_url) {
        Url u = new Url();
        u.setLong_url(long_url);
        u.setShort_url(short_url);
        this.save(u);
        return u;
    }

}