import firstSimulationRequests.{authenticity_token, challenger, headersFirstStep}
import io.gatling.core.Predef._
import io.gatling.http.Predef._


package object firstSimulationRequests {

  val authenticity_token = " "
  val challenger = " "
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
    "accept" -> "*/*",
    "pragma" -> "no-cache",
    "sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="98", "Google Chrome";v="98""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "Windows",
    "sec-fetch-dest" -> "empty",
    "sec-fetch-mode" -> "cors",
    "sec-fetch-site" -> "same-origin",
    "x-requested-with" -> "XMLHttpRequest"
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

    val openHomePage = exec(http("Open Home Page")
      .get("/")
      .headers(headersHomePage)
      .check(status.is(200)))



    val clickOnNextButton = exec(http("Click on Start button")
      .post("/start")
      .headers(headersHomePage)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", authenticity_token)
      .formParam("challenger[step_id]", challenger)
      .formParam("challenger[step_number]", "1")
      .formParam("commit", "Start")
      .check(status.is(302)))
  }


  object Step2 {

    val openStep2Page = exec(http("Open Step 2 page")
      .get("/step/2")
      .headers(headersFirstStep)
      .check(status.is(200)))

    val clickOnNextButton = exec(http("Click on Start button")
      .post("/start")
      .headers(headersFirstStep)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "pQrnAmPZJtu/F2djd9goQz9dzI9tBqUmw6N34kpWRp0=")
      .formParam("challenger[step_id]", "MC9QQUFseE50N0ZmTVM2eVUwREdSZz09LS1tU0taZVlmRVA2Z3pvYzZDbTZMeFV3PT0=--fbb8f0ffdcf3457d2cec59f329525698070672c7")
      .formParam("challenger[step_number]", "2")
      .formParam("challenger[age]", "31")
      .formParam("commit", "Next")
      .check(status.is(302)))

  }

  object Step3  {

    val openStep3Page = exec(http("Open Step 3 page")
      .get("/step/3")
      .headers(headersChangeAgeStep)
      .check(status.is(200)))

    val clickOnNextButton = exec(http("Click on Start button")
      .post("/start")
      .headers(headersChangeAgeStep)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "pQrnAmPZJtu/F2djd9goQz9dzI9tBqUmw6N34kpWRp0=")
      .formParam("challenger[step_id]", "YzRBUFVwNUtWQ2t6bE5LNE1jMGk5dz09LS1oeTh3NHZoY3VPejJCVTRhVmE0TWtRPT0=--62571860bf3ea0f1f8e23f57b494b6ec93c84f19")
      .formParam("challenger[step_number]", "3")
      .formParam("challenger[largest_order]", "203")
      .formParam("challenger[order_selected]", "MjU4NzQ5Qk54T1NiQXFudDFub25pdz09LS1KMGlVV0VOdTRmNmE0MlAraEErVENnPT0=--aed390ae17f7be1f9d464acd19dd20ad8fc60675")
      .formParam("commit", "Next"))

  }


}