package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.routing.JavaScriptReverseRouter;

public class RoutesController extends Controller {
    // public Result javascriptRoutes(Http.Request request){
    //     return ok(
    //         JavaScriptReverseRouter.create(
    //             "jsRoutes",
    //             "superagent",
    //             request.host(),
    //             controllers.routes.javascript.InitController.Hello()
    //         )
    //     ).as(Http.MimeTypes.JAVASCRIPT);
    // }
}