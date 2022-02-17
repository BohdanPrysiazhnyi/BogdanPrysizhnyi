package firstSimulation

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import firstSimulationRequests._

class Test extends Simulation {

  val th_min = 1
  val th_max = 2
  val test_duration = System.getProperty("duration", "60").toInt
  val test_users = System.getProperty("users", "1").toInt

  val httpProtocol = http
    .baseUrl("https://challenge.flood.io")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("I AM ROBOT")
    .disableFollowRedirect

  val demo = exec(HomePage.openHomePage)
    .pause(th_min, th_max)
    .exec(HomePage.clickOnNextButton)
    .pause(th_min,th_max)
    .exec(Step2.openStep2Page)
    .pause(th_min,th_max)
    .exec(Step2.clickOnNextButton)
    .pause(th_min,th_max)
    .exec(Step3.openStep3Page)
    .pause(th_min,th_max)
    .exec(Step3.clickOnNextButton)
    .pause(th_min,th_max)

  val scn = scenario("Test")
    .during(test_duration.seconds, exitASAP = false) {
      exec(HomePage.openHomePage).pause(th_min, th_max)
    }
  setUp(scn.inject(
    rampConcurrentUsers(5).to(test_users).during(test_duration)
  )).protocols(httpProtocol)
}