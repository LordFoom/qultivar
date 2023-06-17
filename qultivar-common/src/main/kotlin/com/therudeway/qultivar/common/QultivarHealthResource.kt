// QultivarHealthResource.kt
package com.therudeway.qultivar.common

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.Config

abstract class QultivarHealthResource {
    protected val logger = LoggingUtils.logger<QultivarHealthResource>()

    @Inject
    lateinit var config: Config

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): QultivarHealthStatus {
        logger.info("QultivarHealthResource.checkHealth() called")

        val serviceName = getServiceName()
        var serviceRunning: Boolean = true
        var errorMessage: String? = null

        val healthStatus = QultivarHealthStatus(serviceName, serviceRunning, errorMessage)

        return healthStatus
    }

    protected fun getServiceName(): String {
        return config.getValue("qultivar.service_name", String::class.java)
    }
}
