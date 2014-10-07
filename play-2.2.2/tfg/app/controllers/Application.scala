package controllers

import play.api._
import play.api.mvc._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext._

object Application extends Controller {

  def index = Action {

		 val logFile = "sample.txt"
		 val sc = new SparkContext("local", "Simple App", "/home/eruizgar/play-2.2.2/tfg/app",
		 List("target/scala-2.10/tfg_2.10-1.0-SNAPSHOT-sources.jar"))
		 val logData = sc.textFile(logFile, 2).cache()
		 //Con este codigo se filtraba el numero de the
		 val numTHEs = logData.filter(line => line.contains("the")).count()

		 //Este codido nos cuenta el numero de palabras, para ello las corta cada vez que estan separadas por un espacion.
		var lines = logData.flatMap(line => line.split(" "))
		var mapper = lines.map(word => (word,1))
		var reducer = mapper.reduceByKey((a,b) => a+b)
		val result = reducer.collect()

		//Cuenta el numero de lineas del texto
		//var lineas= logData.count()

		//aqui salia por pantalla el numero de the
		// println("Lines with the: %s".format(numTHEs))

		//Sacamos por pantalla el numero de palabras y tambien sacamos un archivo de texto que se guarda en la carpeta results
		println("el numero de palabras es: %s".format(reducer))
		
		//reducer.saveAsTextFile("results")

Ok("Lines with the: %s".format(numTHEs))

   // Ok(views.html.index("Your new application is ready."))
  }

}