import firstSimulationRequests.{ headersFirstStep}
import io.gatling.core.Predef._
import io.gatling.http.Predef._


package object firstSimulationRequests {

  val headersHomePage = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "origin" -> "https://challenge.flood.io",
    "pragma" -> "no-cache",
    "sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="98", "Google Chrome";v="98""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "Windows",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1"
  )

  val headersFirstStep = Map(
    "accept" -> "text/css,*/*;q=0.1",
    "pragma" -> "no-cache",
    "sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="98", "Google Chrome";v="98""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "Windows",
    "sec-fetch-dest" -> "style",
    "sec-fetch-mode" -> "no-cors",
    "sec-fetch-site" -> "cross-site",
    "x-client-data" -> "CJe2yQEIprbJAQjEtskBCKmdygEInvnLAQjnhMwBCNKPzAEIwpfMAQ=="
  )
  val headersChangeAgeStep = Map(
    "origin" -> "https://challenge.flood.io",
    "pragma" -> "no-cache",
    "sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="98", "Google Chrome";v="98""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "Windows",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1"
  )
  val headersNextStep = Map(
    "Origin" -> "https://challenge.flood.io",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "same-origin",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1"
  )
  val headersEnterTokenStep = Map(
    "accept" -> "text/html, application/xhtml+xml",
    "pragma" -> "no-cache",
    "sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="98", "Google Chrome";v="98""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "Windows",
    "sec-fetch-dest" -> "empty",
    "sec-fetch-mode" -> "cors",
    "sec-fetch-site" -> "same-origin",
    "turbolinks-referrer" -> "https://challenge.flood.io/done"
  )

  object HomePage {

    val openHomePage = exec(http("Open Home page")
        .get("/")
        .headers(headersHomePage)
        .check(regex("name=\"authenticity_token\" type=\"hidden\" value=\"(.*?)\"").find.saveAs("authenticity_token"))
        .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
        .check(status.is(200)))
      .pause(7)

    val clickOnNextButton = exec(http("Click on start button")
      .post("/start")
      .headers(headersHomePage)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "1")
      .formParam("commit", "Start")
      .check(status.is(302)))
      .pause(6)
  }


  object Step2 {

    val openStep2Page = exec(http("Open page with age dropdown list")
      .get("/step/2")
      .headers(headersFirstStep)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
      .check(status.is(200)))

    val clickOnNextButton = exec(http("Choose age from dropDown list and click Next")
      .post("/start")
      .headers(headersFirstStep)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "2")
      .formParam("challenger[age]", "31")
      .formParam("commit", "Next")
      .check(status.is(302)))
      .pause(3)

  }

  object Step3  {

    val openStep3Page = exec(http("Open page where choose highest value from list")
      .get("/step/3")
      .headers(headersChangeAgeStep)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
      .check(css(".collection_radio_buttons").findAll.transform(list => list.map(_.toInt).max).saveAs("largest_order"))
      .check(regex(".*order_selected.*value=\"(.*?)\"").find.saveAs("order_selected"))
      .check(status.is(200)))

    val clickOnNextButton = exec(http("Choose largest order and send it")
      .post("/start")
      .headers(headersChangeAgeStep)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authenticity_token}")
      .formParam("challenger[step_id]", "${step_id}")
      .formParam("challenger[step_number]", "3")
      .formParam("challenger[largest_order]", "${largest_order}")
      .formParam("challenger[order_selected]", "${order_selected}")
      .formParam("commit", "Next")
      .check(status.is(302)))
      .pause(2)

  }
  object Step4 {
   val openStep4Page = exec(http("Open step 4 page")
      .get("/step/4")
      .headers(headersNextStep)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
     .check(status.is(200)))
   val clickOnNextButtonOnStep4Page= exec(http("Click on next button on step 4 page")
        .post("/start")
        .headers(headersNextStep)
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

  }
  object Step5 {
   val openStep5Page = exec(http("Open step 5 page with one time token")
      .get("/step/5")
      .headers(headersEnterTokenStep)
      .check(regex(".*step_id.*value=\"(.*?)\"").find.saveAs("step_id"))
      .check(regex(".*token.*").find.saveAs("one_time_token"))
      .check(status.is(200)))
     val sendTokenAndClickNext = exec(http("Send one time token and click Next button")
        .post("/start")
        .headers(headersEnterTokenStep)
        .formParam("utf8", "✓")
        .formParam("authenticity_token", "${authenticity_token}")
        .formParam("challenger[step_id]", "${step_id}")
        .formParam("challenger[step_number]", "5")
        .formParam("challenger[one_time_token]", "${one_time_token}")
        .formParam("commit", "Next")
        .check(status.is(302)))
      .pause(1)
  }


}