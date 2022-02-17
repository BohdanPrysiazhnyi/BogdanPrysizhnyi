package firstSimulation

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
		.exec(http("request_0")
			.get("/")
			.headers(headers_0))
		.pause(7)
		.exec(http("request_1")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "ylfzfZNclA8w/7fIGPxMajWAM4kWZ5VFlMUnVLdqJEE=")
			.formParam("challenger[step_id]", "STFWWkFVN211Q0xrWDlPZ3NSQ21Fdz09LS1qQTVHQ0dOblpNWkFJamM0aHJwUE5BPT0=--12d6d8366872c2401b956f13c544d8e220a727ea")
			.formParam("challenger[step_number]", "1")
			.formParam("commit", "Start"))
		.pause(6)
		.exec(http("request_2")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "ylfzfZNclA8w/7fIGPxMajWAM4kWZ5VFlMUnVLdqJEE=")
			.formParam("challenger[step_id]", "N25qdlVuNWxNd0FvaVZWYnNMSkxqUT09LS1aL0tUQjFCbTFzbmIvT3RJNjErMlJ3PT0=--84e2bc10d09e4c4ca0f5eeb2e476c0836a9e89fc")
			.formParam("challenger[step_number]", "2")
			.formParam("challenger[age]", "31")
			.formParam("commit", "Next"))
		.pause(3)
		.exec(http("request_3")
			.post("/start")
			.headers(headers_1)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "ylfzfZNclA8w/7fIGPxMajWAM4kWZ5VFlMUnVLdqJEE=")
			.formParam("challenger[step_id]", "RmFGK09WUDJPMXphY1QzZjBKckZxdz09LS03M3F4Z3gySHgxeWdZVWtZUGwxZ2tRPT0=--da21170769b8f0de549416a0f1d5299a866e337d")
			.formParam("challenger[step_number]", "3")
			.formParam("challenger[largest_order]", "")
			.formParam("challenger[order_selected]", "RUZ4VFV1TlVUazJ1KzZXMEdRZDFDZz09LS1pOVhkYjJvOVQ1TXhnMFNwZ3JWUEJRPT0=--459a85f0f096ae74903fd29827580b6b2895665c")
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