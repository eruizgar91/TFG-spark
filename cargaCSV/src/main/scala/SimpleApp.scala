import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import au.com.bytecode.opencsv.CSVParser
import org.apache.spark.rdd.RDD

import scala.Tuple2;
 
object SimpleApp {
 def main(args: Array[String]) {
 	 val sc = new SparkContext("local", "cargaCSV", "/home/eruizgar/Descargas/spark-1.0.0",
 			List("target/scala-2.10/simple-project_2.10-1.0.jar"))
 	
 /* //Este codigo carga el csv y cuenta cuantas palabras hay de cada tipo y lo guarda en un archivo de texto.
 	val logFile = "src/main/carga.csv"

 	val data = sc.textFile(logFile, 2).cache()

 	val parsedData = data.flatMap(line => line.split(','))
 	var mapper = parsedData.map(word => (word,1))
 	var reducer = mapper.reduceByKey((a,b) => a+b)	 
	reducer.saveAsTextFile("resultas")*/



	//val data = sc.textFile("src/main/carga.csv")
	val data =sc.textFile("src/main/gasolineras-ca-ceres-csv.csv")

/*  //Imprime 3 filas separas por comas.
	data.map(line => {
         val parser = new CSVParser(',')
         parser.parseLine(line).mkString(",")
       }).take(3).foreach(println)
*/

	data.mapPartitions(lines => {
         val parser=new CSVParser(',')
         lines.map(line => {
           val columns = parser.parseLine(line)
           
           Array(columns(2),columns(4)).mkString(",")
         })
       }).countByValue().toList.sorted.foreach(println)	
	

	//De este modo filtramos la primera linea de la cuenta. Esta es la forma en la que lo hacia sin importar openCSV.
	//var data1 = data.filter(lines => !lines.startsWith("buenos dias"))
	//var	data2 = data1.map(line => line.split(","))
	//var data3 = data2.count()
	//println(data2.first())
	//Coge los 3 valores y los imprime por pantalla.
	//data2.foreach(println);
    //println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA  " + data3)          
 }
}

