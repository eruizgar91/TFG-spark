package controllers

import play.api._
import play.api.mvc._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

import au.com.bytecode.opencsv.CSVParser
import org.apache.spark.rdd.RDD

import scala.Tuple2;



object Application extends Controller {

  def index = Action {

		 val sc = new SparkContext("local", "Simple App", "/home/eruizgar/play-2.2.2/tfg/app",
		 List("target/scala-2.10/tfg_2.10-1.0-SNAPSHOT.jar"))

		 val data = sc.textFile("carga.csv")

	
		
	val z = data.mapPartitions(lines => {
         val parser = new CSVParser(',')
         lines.map(line => {
           val columns = parser.parseLine(line)
           
           Array(columns(2),columns(1)).mkString(",")
         })
       })

  val l = data.mapPartitions(lines => {
         val parser = new CSVParser(',')
         lines.map(line => {
           val columns = parser.parseLine(line)
           
           Array(columns(0)).mkString(",")
         })
       })

	val x = z.collect.mkString(" <br> ")
  val p = l.collect.mkString(" </option><option value=''> ")

	

  Ok(views.html.index(x,p))

   // Ok(views.html.index("Your new application is ready."))
  }

}