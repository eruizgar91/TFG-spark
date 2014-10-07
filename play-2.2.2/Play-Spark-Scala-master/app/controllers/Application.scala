package controllers

import play.api._
import play.api.mvc._

import utils.SimpleUtility
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
 

object Application extends Controller {

  def index = Action {
   // Future{SimpleUtility.simpleApp}
    //Ok(views.html.index(""))
    val logFile = "README.md"
  	val conf = new SparkConf(false) // skip loading external settings
      .setMaster("local[4]") // run locally with enough threads
      .setAppName("firstSparkApp")
      .set("spark.logConf", "true")
      .set("spark.driver.host", "localhost")
    val sc = new SparkContext(conf)
 	val logData = sc.textFile(logFile, 2).cache()
 	val numSparks = logData.filter(line => line.contains("play")).count()
 	println("Lines with Play: " + numSparks) //para probar que valor coge al filtrar, pero no lo entiendo bien
    Ok(views.html.index("Lines with Play: " + numSparks))

  }

}
