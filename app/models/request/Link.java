package models.request;

import javax.validation.constraints.NotNull;

public class Link{
    
    @NotNull
    private String long_url;

    public String getLong_url(){
        return long_url;
    }

    public void setLong_url(String long_url){
        this.long_url = long_url;
    }

}