package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mongodb.morphia.annotations.*;
import models.mongo.Common;

@Entity("link")
@Indexes({
    @Index(
        fields = {
            @Field(value = "short_url")
        },
        options = @IndexOptions(unique = true))
    })
public class Url extends Common {
  
    private String long_url;
    private String short_url;  

    public  Url() {}
    public  Url(String long_url, String short_url)
    {
        super();
        this.long_url = long_url;
        this.short_url = short_url;
    }

    public String getLong_url(){
        return long_url;
    }
    public void setLong_url(String value){
        this.long_url = value;
    }
    public String getShort_url(){
        return short_url;
    }
    public void setShort_url(String value){
        this.short_url = value;
    }

}