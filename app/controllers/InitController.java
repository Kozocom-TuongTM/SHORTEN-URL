package controllers;

// import be.objectify.deadbolt.java.actions.SubjectPresent;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Singleton;
import models.Data;
import play.libs.Json;



@Singleton
public class InitController extends Controller {
    
    // @SubjectPresent
    public Result Hello () {
        String data = "Hello world";
        Data dataToReturn = new Data(data);
        System.out.println(data);
        return ok(Json.toJson(dataToReturn));
    }
}