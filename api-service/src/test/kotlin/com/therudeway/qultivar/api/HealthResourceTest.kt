// HealthResourceTest.kt
package com.therudeway.qultivar.api

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

@QuarkusTest
class HealthResourceTest {

    @Test
    fun testHealthCheck() {
        val response = RestAssured.given()
            .`when`().get("/health")
            .then().statusCode(200)
            .contentType(ContentType.JSON)
            .body("serviceName", Matchers.`is`("api-service"))
            .body("serviceRunning", Matchers.`is`(true))
            .body("errorMessage", Matchers.nullValue())
            .extract().response()

        val responseBody = response.body.asString()
        println("Response Body: $responseBody")
    }
}
