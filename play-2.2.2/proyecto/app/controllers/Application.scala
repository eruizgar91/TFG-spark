package controllers

import play.api._
import play.api.mvc._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object Application extends Controller {
  
  def index = Action {
  	val logFile = "home/eruizgar/play-2.2.2/README.md"
  	val sc = new SparkContext("local", "Application", "/home/eruizgar/Descargas/spark-1.0.0", List("target/scala-2.10/playsparkapp_2.10-1.0-SNAPSHOT.jar"))
 	val logData = sc.textFile(logFile, 2).cache()
 	val numSparks = logData.filter(line => line.contains("Spark")).count()

 	Ok(views.html.index("Lines with Spark: " + numSparks))
  }
  
}