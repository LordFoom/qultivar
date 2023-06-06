// HealthResource.kt
package com.therudeway.qultivar.common

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.json.bind.annotation.JsonbProperty
import org.eclipse.microprofile.config.Config

data class HealthStatus(
    @JsonbProperty("serviceName") val serviceName: String,
    @JsonbProperty("serviceRunning") val serviceRunning: Boolean,
    @JsonbProperty("errorMessage") val errorMessage: String?
)

abstract class AbstractHealthResource {
    protected val logger = LoggingUtils.logger<AbstractHealthResource>()

    @Inject
    lateinit var config: Config

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): Response {
        logger.info("AbstractHealthResource.checkHealth() called")

        val serviceName = getServiceName()
        var serviceRunning: Boolean = true
        var errorMessage: String? = null

        val healthStatus = HealthStatus(serviceName, serviceRunning, errorMessage)

        return Response.ok(healthStatus).build()
    }

    protected fun getServiceName(): String {
        return config.getValue("qultivar.service_name", String::class.java)
    }
}
