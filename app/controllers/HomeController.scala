package controller;

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._
import java.lang.String;

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def appHello(name : String) = Action {
    implicit request =>
    val name: Option[String] = request.getQueryString("name")
    val result: String = name.get + " hello"
    Ok(Json.obj("url" -> result))
  }
  
  def appSummary = Action {
    Ok(Json.obj("content" -> "Link short url"))
  }
}
