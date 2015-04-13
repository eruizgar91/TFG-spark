package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

import au.com.bytecode.opencsv.CSVParser
import org.apache.spark.rdd.RDD

import scala.Tuple2;



object Application extends Controller {
  val sc = new SparkContext("local", "Simple App", "/home/eruizgar/play-2.2.2/tfg/app",
     List("target/scala-2.10/tfg_2.10-1.0-SNAPSHOT.jar"))

     //val data = sc.textFile("carga.csv")
     val data = sc.textFile("gasolineras-ca-ceres-csv.csv")

     case class Gasolinera (URI: String, om_situadoEnVia: String, rdfs_label: String, om_tieneEnlaceSIG : String, geo_long: String , geo_lat: String)
  

  def index = Action {

		 
		
	val z = data.mapPartitions(lines => {
         val parser = new CSVParser(',')
         lines.map(line => {
           val columns = parser.parseLine(line)
           
           Array(columns(2),columns(1)).mkString("-----")
         })
       })

  val l = data.mapPartitions(lines => {
         val parser = new CSVParser(',')
        lines.map(line => {
           val columns = parser.parseLine(line)
           Array(columns(0)).mkString(",")
         })

       })
    var prueba = data.map(_.split(",")).map(
        r => (r(0), r(1), r(2), r(3), r(4), r(5))
    ).take(5)


	val x = z.collect
  val p = l.collect

	

  Ok(views.html.index(x,p,prueba))

   // Ok(views.html.index("Your new application is ready."))
  }
  
  //NOVEDADES!!!!!
   val form = Form(
    single("opcion" -> text)    
  )
   //Cuando pulsamos el botón con la gasolinera elegida nos envia a la página con el mapa.
  def submit = Action { implicit request =>
    val fname = form.bindFromRequest
    val a=fname.get

    var prueba = data.map(_.split(",")).map(
        r => (r(0),r(1),r(2),r(3),r(4),r(5))
    ).take(10)
    
    //Crear metodo que compare si el valor recibido esta en algun r(0) y devolver todos los datos de la gasolinera elegida.
   
   var long=""
   var lat=""
   
   for(i <- 0 until prueba.length){
    println(prueba(i)._1 + '"' + a +'"')
    
    if(prueba(i)._1=='"' + a +'"'){
      long=prueba(i)._5
      lat=prueba(i)._6
    }
   }
   

    Ok(views.html.pagina(long,lat))
  }


}