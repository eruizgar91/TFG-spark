import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import scala.Tuple2;
 
object SimpleApp {
 def main(args: Array[String]) {
 val logFile = "src/main/sample.txt"
 val sc = new SparkContext("local", "Simple App", "/home/eruizgar/Descargas/spark-1.0.0",
 List("target/scala-2.10/simple-project_2.10-1.0.jar"))
 val logData = sc.textFile(logFile, 2).cache()
 
 //Con este codigo se filtraba el numero de the
 //val numTHEs = logData.filter(line => line.contains("the")).count()

 //Este codido nos cuenta el numero de palabras, para ello las corta cada vez que estan separadas por un espacion.
 var lines = logData.flatMap(line => line.split(" "))
 var mapper = lines.map(word => (word,1))
 var reducer = mapper.reduceByKey((a,b) => a+b)
 val result = reducer.collect()
 
//aqui salia por pantalla el numero de the
// println("Lines with the: %s".format(numTHEs))

//Sacamos por pantalla el numero de palabras y tambien sacamos un archivo de texto que se guarda en la carpeta results
println("el numero de palabras es: %s".format(reducer))

reducer.saveAsTextFile("results")
 }
}
