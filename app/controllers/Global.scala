package controllers

import play.api._
import mvc.Controller

trait DynamicGlobal extends GlobalSettings {

  lazy val home = new Home with Welcome

  val controllers: Map[Class[_ <: Controller], _ <: Controller] = Map(classOf[Home] -> home)

  override def getControllerInstance[A](controllerClass: Class[A]) : A = controllers.get(controllerClass.asInstanceOf[Class[_ <: Controller]]) match {
    case Some(controller) => controller.asInstanceOf[A]
    case _ => super.getControllerInstance(controllerClass)
  }
}

object Global extends DynamicGlobal
