// FeedServiceEndpoint.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.LoggingUtils
import com.therudeway.qultivar.feed.Grow
import jakarta.inject.Inject
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.DELETE
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
            return Response.ok(feedServiceClient.getGrowStages()).build()
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

    @GET
    @Path("/feed/event")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedEvents(@HeaderParam("Authorization") authHeader: String): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getFeedEvents()).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/feed/event/grow/{growId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedEventsByGrowId(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("growId") growId: Long): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getFeedEventsByGrowId(growId)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/feed/grow")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrows(@HeaderParam("Authorization") authHeader: String): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getGrows()).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @POST
    @Path("/feed/grow")
    @Produces(MediaType.APPLICATION_JSON)
    fun createGrow(@HeaderParam("Authorization") authHeader: String, grow: Grow): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.createGrow(grow)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @PUT
    @Path("/feed/grow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun updateGrow(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("id") id: Long,
        grow: Grow
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.updateGrow(id, grow)).build()
        } catch (e: BadRequestException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @DELETE
    @Path("/feed/grow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteGrow(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("id") id: Long
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.deleteGrow(id)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/feed/grow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowById(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("id") id: Long
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getGrowById(id)).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/feed/grow/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowsByUser(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("userId") userId: Long
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(feedServiceClient.getGrowsByUserId(userId)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }
}
