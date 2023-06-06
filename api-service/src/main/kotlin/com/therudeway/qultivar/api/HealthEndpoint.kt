// ServiceHealthEndpoint.kt
package com.therudeway.qultivar.api

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.therudeway.qultivar.common.LoggingUtils
import com.therudeway.qultivar.common.HealthStatus

@Path("/api/health")
public class ServiceHealthEndpoint {
    private val logger = LoggingUtils.logger<ServiceHealthEndpoint>()

    @Inject
    @RestClient
    lateinit var apiServiceClient: ApiServiceClient

    @Inject
    @RestClient
    lateinit var authServiceClient: AuthServiceClient

    @Inject
    @RestClient
    lateinit var feedServiceClient: FeedServiceClient

    @Inject
    @RestClient
    lateinit var mediaServiceClient: MediaServiceClient

    @Inject
    @RestClient
    lateinit var userServiceClient: UserServiceClient

    // get the health for all the services
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getServiceHealth(): List<HealthStatus> {

        val healthStatuses = mutableListOf<HealthStatus>()
        healthStatuses.add(getApiServiceHealth())
        healthStatuses.add(getAuthServiceHealth())
        healthStatuses.add(getFeedServiceHealth())
        healthStatuses.add(getMediaServiceHealth())
        healthStatuses.add(getUserServiceHealth())
        return healthStatuses
    }

    @GET
    @Path("/api-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getApiServiceHealth(): HealthStatus {
        try {
            return apiServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("api-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/auth-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAuthServiceHealth(): HealthStatus {
        try {
            return authServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("auth-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/feed-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedServiceHealth(): HealthStatus {
        try {
            return feedServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("feed-service", false, e.message)
            return healthStatus
        }
    }

    @GET
    @Path("/media-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMediaServiceHealth(): HealthStatus {
        try {
            return mediaServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("media-service", false, e.message)
            return healthStatus
        }
    }


    @GET
    @Path("/user-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserServiceHealth(): HealthStatus {
        try {
            return userServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("user-service", false, e.message)
            return healthStatus
        }
    }
}
