name := "proyecto"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "1.0.0",
"com.typesafe.akka" %% "akka-actor" % "2.2.3",
"com.typesafe.akka" %% "akka-slf4j" % "2.2.3",
"org.apache.spark" %% "spark-streaming-twitter" % "1.0.1"
)

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

