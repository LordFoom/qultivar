// UserServiceEndpoint.kt
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
public class UserServiceEndpoint {
    private val logger = LoggingUtils.logger<UserServiceEndpoint>()

    @Inject @RestClient lateinit var authServiceClient: AuthServiceClient
    @Inject @RestClient lateinit var userServiceClient: UserServiceClient

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsers(@HeaderParam("Authorization") authHeader: String): Response {
        try {
            val isValidToken = authServiceClient.validateToken(authHeader)
            logger.info(isValidToken.toString())
            return Response.ok(userServiceClient.getAll()).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserById(@PathParam("id") id: Long): Response {
        try {
            return Response.ok(userServiceClient.getById(id)).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/user/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserByEmail(@PathParam("email") email: String): Response {
        try {
            return Response.ok(userServiceClient.getByEmail(email)).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }
}
