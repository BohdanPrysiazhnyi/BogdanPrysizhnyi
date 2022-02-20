package firstSimulation

import io.gatling.commons.Exclude.list

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ChallengeFloodSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://challenge.flood.io")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:97.0) Gecko/20100101 Firefox/97.0")

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
    .exec(http("click on start button")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${challenger[step_id]}")
      .formParam("challenger[step_number]", "1")
      .formParam("commit", "Start"))
    .pause(6)
    .exec(http("Open page with age dropdown list")
      .get("/step/2")
      .headers(headers_0)
			.check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id")))
    .exec(http("choose age from dropDown list and click Next")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "2")
      .formParam("challenger[age]", "31")
      .formParam("commit", "Next"))
    .pause(3)
		.exec(http("Open page where choose highest value from list")
			.get("/step/3")
			.headers(headers_0)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
      .check(regex(".*collection_radio_buttons.*for=\"(.*?)\"*").findAll.transform(list => list.map(_.toInt).max).saveAs("order_selected"))
    .check(regex(".*collection_radio_buttons.*for=\"(.*?)\"")))
    .exec(http("request_3")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "3")
      .formParam("challenger[largest_order]", "${order_selected}")
      .formParam("challenger[order_selected]", "${order_selected}")
      .formParam("commit", "Next"))
    .pause(2)
    .exec(http("request_4")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "gHU0wP9lm2X0fRgV8nj6uzR2mdFTQHlSPyd7/UfNGBE=")
      .formParam("challenger[step_id]", "RnpVbk5hcXh2c0x1VlRobzVuQUo2Zz09LS1XRTVIMGVkdHUyemlYYmRhRFZ6ZHl3PT0=--5bf41a1b8679a26e23d420bae549e9f31fe795da")
      .formParam("challenger[step_number]", "1")
      .formParam("commit", "Start"))
    .pause(5)
    .exec(http("request_5")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "gHU0wP9lm2X0fRgV8nj6uzR2mdFTQHlSPyd7/UfNGBE=")
      .formParam("challenger[step_id]", "Z1B6UlQwZ2lHbzRtL3grRWZtNVJmZz09LS1oaWdTTFk1WHRlYTBXQWlLYkJSbUFnPT0=--6bfd032aa1e02f044bd37918987d3a0f7e56c319")
      .formParam("challenger[step_number]", "2")
      .formParam("challenger[age]", "31")
      .formParam("commit", "Next"))
    .pause(5)
    .exec(http("request_6")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "gHU0wP9lm2X0fRgV8nj6uzR2mdFTQHlSPyd7/UfNGBE=")
      .formParam("challenger[step_id]", "K3pTRkNMbUxublAyd3d4YzNRcDRwQT09LS0yQjhKSTFuRWs5R2hxNWdncjk1b0VnPT0=--64ae41ba85d8722c06954723f2d241e7593516ab")
      .formParam("challenger[step_number]", "3")
      .formParam("challenger[largest_order]", "271")
      .formParam("challenger[order_selected]", "azhzSTY5NDFKdDlYdFMzUVpLalJTUT09LS1WRXAycGFqRXJxWUR0QXZPOVFvY0VnPT0=--c72ce3977a8ad96aba5b05c74d66bbef8445ac43")
      .formParam("commit", "Next"))
    .pause(1)
    .exec(http("request_7")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "gHU0wP9lm2X0fRgV8nj6uzR2mdFTQHlSPyd7/UfNGBE=")
      .formParam("challenger[step_id]", "aDNkV1ZyWW1OeDdiMWJEU1o2cjNmdz09LS1vbmEyMUlJNG9VWWxTRlJNUXdWSEFBPT0=--cc20332c95df80c032ed1f75ca3a939ac4935f15")
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
      .resources(http("request_8")
        .get("/code")
        .headers(headers_8)))
    .pause(6)
    .exec(http("request_9")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "gHU0wP9lm2X0fRgV8nj6uzR2mdFTQHlSPyd7/UfNGBE=")
      .formParam("challenger[step_id]", "QXYvdGFwZW1uL1hwL0JuVE05WVBoZz09LS1XdUFDMTlkU0dRZXFVT2g0RXNZbzRRPT0=--685a0d3a42863fe50019a8b7602627ecb576437e")
      .formParam("challenger[step_number]", "5")
      .formParam("challenger[one_time_token]", "3345678976")
      .formParam("commit", "Next"))
    .pause(1)
    .exec(http("request_10")
      .get("/")
      .headers(headers_10))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}