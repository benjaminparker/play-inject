package controllers

import play.api.mvc._

trait Home extends Controller {

  this: Welcome =>

  def welcome = Action {
    Ok(views.html.index(message))
  }
}

trait Welcome {
  def message = "Your new application is ready."
}
