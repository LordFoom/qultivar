// HealthEndpoint.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.QultivarHealthStatus
import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/api/health")
public class ServiceHealthEndpoint {
    private val logger = LoggingUtils.logger<ServiceHealthEndpoint>()

    @Inject @RestClient lateinit var apiServiceClient: ApiServiceClient

    @Inject @RestClient lateinit var authServiceClient: AuthServiceClient

    @Inject @RestClient lateinit var feedServiceClient: FeedServiceClient

    @Inject @RestClient lateinit var mediaServiceClient: MediaServiceClient

    @Inject @RestClient lateinit var userServiceClient: UserServiceClient

    @Inject @RestClient lateinit var exampleServiceClient: ExampleServiceClient

    // get the health for all the services
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getServiceHealth(): List<QultivarHealthStatus> {
        val healthStatuses = mutableListOf<QultivarHealthStatus>()
        healthStatuses.add(getApiServiceHealth())
        healthStatuses.add(getAuthServiceHealth())
        healthStatuses.add(getFeedServiceHealth())
        healthStatuses.add(getMediaServiceHealth())
        healthStatuses.add(getUserServiceHealth())
        healthStatuses.add(getExampleServiceHealth())
        return healthStatuses
    }

    @GET
    @Path("/api-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getApiServiceHealth(): QultivarHealthStatus {
        try {
            return apiServiceClient.checkHealth()
        } catch (e: Exception) {
            logger.info(e.stackTraceToString())
            val healthStatus = QultivarHealthStatus("api-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/auth-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAuthServiceHealth(): QultivarHealthStatus {
        try {
            return authServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = QultivarHealthStatus("auth-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/feed-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedServiceHealth(): QultivarHealthStatus {
        try {
            return feedServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = QultivarHealthStatus("feed-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/media-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMediaServiceHealth(): QultivarHealthStatus {
        try {
            return mediaServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = QultivarHealthStatus("media-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/user-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserServiceHealth(): QultivarHealthStatus {
        try {
            return userServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = QultivarHealthStatus("user-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/example-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getExampleServiceHealth(): QultivarHealthStatus {
        try {
            return exampleServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = QultivarHealthStatus("example-service", false, e.message)
            return healthStatus
        }
    }
}
