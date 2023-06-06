// ApiResource.kt
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

@Path("/api")
public class ApiResource {
    private val logger = LoggingUtils.logger<ApiResource>()

    // get the health for all the services
    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun getServiceHealth(): Response {
        val apiServiceHealth = getApiServiceHealth()
        val authServiceHealth = getAuthServiceHealth()
        val feedServiceHealth = getFeedServiceHealth()
        val mediaServiceHealth = getMediaServiceHealth()
        val userServiceHealth = getUserServiceHealth()

        val healthStatuses = mutableListOf<HealthStatus>()
        healthStatuses.add(apiServiceHealth.readEntity(HealthStatus::class.java))
        healthStatuses.add(authServiceHealth.readEntity(HealthStatus::class.java))
        healthStatuses.add(feedServiceHealth.readEntity(HealthStatus::class.java))
        healthStatuses.add(mediaServiceHealth.readEntity(HealthStatus::class.java))
        healthStatuses.add(userServiceHealth.readEntity(HealthStatus::class.java))

        return Response.ok(healthStatuses).build()
    }

    /*********************** 
     * api-service
    ***********************/
    @Inject
    @RestClient
    lateinit var apiServiceClient: ApiServiceClient

    @GET
    @Path("/health/api-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getApiServiceHealth(): Response {
        try {
            return apiServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("api-service", false, e.message)
            return Response.serverError().entity(healthStatus).build()
        }
    }

    /*********************** 
     * auth-service
    ***********************/
    @Inject
    @RestClient
    lateinit var authServiceClient: AuthServiceClient

    @GET
    @Path("/health/auth-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAuthServiceHealth(): Response {
        try {
            return authServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("auth-service", false, e.message)
            return Response.serverError().entity(healthStatus).build()
        }
    }

    /*********************** 
     * feed-service
    ***********************/
    @Inject
    @RestClient
    lateinit var feedServiceClient: FeedServiceClient

    @GET
    @Path("/health/feed-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedServiceHealth(): Response {
        try {
            return feedServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("feed-service", false, e.message)
            return Response.serverError().entity(healthStatus).build()
        }
    }

    /*********************** 
     * media-service
    ***********************/
    @Inject
    @RestClient
    lateinit var mediaServiceClient: MediaServiceClient

    @GET
    @Path("/health/media-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMediaServiceHealth(): Response {
        try {
            return mediaServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("media-service", false, e.message)
            return Response.serverError().entity(healthStatus).build()
        }
    }

    /*********************** 
     * user-service
    ***********************/
    @Inject
    @RestClient
    lateinit var userServiceClient: UserServiceClient


    @GET
    @Path("/health/user-service")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserServiceHealth(): Response {
        try {
            return userServiceClient.checkHealth()
        } catch (e: Exception) {
            val healthStatus = HealthStatus("user-service", false, e.message)
            return Response.serverError().entity(healthStatus).build()
        }
    }


    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsers(): Response {
        try {
            return userServiceClient.getAll()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }


    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserById(@PathParam("id") id: Long): Response {
        try {
            return userServiceClient.getById(id)
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }


    @GET
    @Path("/user/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserByEmail(@PathParam("email") email: String): Response {
        try {
            return userServiceClient.getByEmail(email)
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }
}
