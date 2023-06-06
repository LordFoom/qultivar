// FeedServiceEndpoint.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/api")
public class FeedServiceEndpoint {
    private val logger = LoggingUtils.logger<FeedServiceEndpoint>()

    @Inject @RestClient lateinit var authServiceClient: AuthServiceClient
    @Inject @RestClient lateinit var feedServiceClient: FeedServiceClient

    @GET
    @Path("/feed/growstage")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStages(@HeaderParam("Authorization") authHeader: String): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getAllGrowStages()).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/feed/growstage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStageById(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("id") id: Long): Response 
    {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getGrowStageById(id)).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/feed/growstage/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStageByName(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("name") name: String): Response 
    {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getGrowStageByName(name)).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }
}
