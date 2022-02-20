package firstSimulation

import io.gatling.commons.Exclude.list

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ChallengeFloodSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://challenge.flood.io")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""",""".*css.*""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:97.0) Gecko/20100101 Firefox/97.0")
    .disableFollowRedirect

  val headers_0 = Map(
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "none",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map(
    "Origin" -> "https://challenge.flood.io",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "same-origin",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_8 = Map(
    "Accept" -> "*/*",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_10 = Map(
    "Accept" -> "text/html, application/xhtml+xml",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin",
    "Turbolinks-Referrer" -> "https://challenge.flood.io/done")


  val scn = scenario("ChallengeFloodSimulation")
    .exec(http("Open Home page")
      .get("/")
      .headers(headers_0)
      .check(regex("name=\"authenticity_token\" type=\"hidden\" value=\"(.*?)\"").find.saveAs("authenticity_token"))
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id")))
    .pause(7)
    .exec(http("Click on start button")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "1")
      .formParam("commit", "Start")
      .check(status.is(302)))
    .pause(6)
    .exec(http("Open page with age dropdown list")
      .get("/step/2")
      .headers(headers_0)
			.check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id")))
    .exec(http("Choose age from dropDown list and click Next")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "2")
      .formParam("challenger[age]", "31")
      .formParam("commit", "Next")
      .check(status.is(302)))
    .pause(3)
		.exec(http("Open page where choose highest value from list")
			.get("/step/3")
			.headers(headers_0)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
      .check(css(".collection_radio_buttons").findAll.transform(list => list.map(_.toInt).max).saveAs("largest_order"))
    .check(regex(".*order_selected.*value=\"(.*?)\"").find.saveAs("order_selected")))
    .exec(http("Choose largest order and send it")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "3")
      .formParam("challenger[largest_order]", "${largest_order}")
      .formParam("challenger[order_selected]", "${order_selected}")
      .formParam("commit", "Next")
      .check(status.is(302)))
    .pause(2)
    .exec(http("Open step 4 page")
      .get("/step/4")
      .headers(headers_0)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id")))
    .exec(http("Click on next button on step 4 page")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "4")
      .formParam("challenger[order_9]", "1645083036")
      .formParam("challenger[order_4]", "1645083036")
      .formParam("challenger[order_7]", "1645083036")
      .formParam("challenger[order_7]", "1645083036")
      .formParam("challenger[order_5]", "1645083036")
      .formParam("challenger[order_13]", "1645083036")
      .formParam("challenger[order_8]", "1645083036")
      .formParam("challenger[order_8]", "1645083036")
      .formParam("challenger[order_11]", "1645083036")
      .formParam("challenger[order_16]", "1645083036")
      .formParam("commit", "Next")
      .check(status.is(302)))
    .pause(6)
    .exec(http("Open step 5 page with one time token")
      .get("/step/5")
      .headers(headers_0)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
    .check(regex(".*token.*").find.saveAs("one_time_token")))
    .exec(http("Send one time token and click Next button")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "5")
      .formParam("challenger[one_time_token]", "${one_time_token}")
      .formParam("commit", "Next")
      .check(status.is(302)))
    .pause(1)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}