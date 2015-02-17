import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import scala.Tuple2;
import twitter4j._


object StatusStreamer {
 def main(args: Array[String]) {
      val twitterStream = new TwitterStreamFactory(Util.config).getInstance
    twitterStream.addListener(simpleStatusListener)
    //Sirve para filtrar los tweets de los ids que aparecen en el array.
    //twitterStream.filter(new FilterQuery(Array(181509642,307491803)))
    twitterStream.sample
    Thread.sleep(100000)
    twitterStream.cleanUp
    twitterStream.shutdown
  }
  
 

  object Util {
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey("vwJdHryiS4glSRUNcJBjMPvNB")
    .setOAuthConsumerSecret("phX0yqx9XSHdGhiHMObyFGhxyeLC4YouoC7g6M8tifMHFSl8ga")
    .setOAuthAccessToken("181509642-QciAlUjifB1fC3MYJOPx7lECVWHAAQUWh8W3bKwZ")
    .setOAuthAccessTokenSecret("x8W7WdkEIlqhMxNqyZpWPLxtd51P5zO4A4OVB5sdnX5vP")
    .build
}
def simpleStatusListener = new StatusListener() {
  def onStatus(status: Status) { println(status.getText) }
  def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
  def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
  def onException(ex: Exception) { ex.printStackTrace }
  def onScrubGeo(arg0: Long, arg1: Long) {}
  def onStallWarning(warning: StallWarning) {}
}
}