name := "Simple Project"
 
version := "1.0"
 
scalaVersion := "2.10.4"
 
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.0.0"

libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"
 
resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
