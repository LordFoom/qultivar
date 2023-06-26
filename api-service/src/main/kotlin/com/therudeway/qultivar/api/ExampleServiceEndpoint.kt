// ExampleServiceEndpoint.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/api/example")
public class ExampleServiceEndpoint {
    private val logger = LoggingUtils.logger<UserServiceEndpoint>()

    @Inject @RestClient lateinit var authServiceClient: AuthServiceClient
    @Inject @RestClient lateinit var exampleServiceClient: ExampleServiceClient

    /** STATIC TABLE */
    @GET
    @Path("/statictable")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStaticTables(@HeaderParam("Authorization") authHeader: String): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(exampleServiceClient.getStaticTables()).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/statictable/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStaticTableById(
            @HeaderParam("Authorization") authHeader: String,
            @PathParam("id") id: Long
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(exampleServiceClient.getStaticTableById(id)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    /** ONE TO MANY PARENT */
    @GET
    @Path("/onetomanyparent")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyParents(@HeaderParam("Authorization") authHeader: String): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(exampleServiceClient.getOneToManyParents()).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/onetomanyparent/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyParentById(
            @HeaderParam("Authorization") authHeader: String,
            @PathParam("id") id: Long
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(exampleServiceClient.getOneToManyParentById(id)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    /** ONE TO MANY CHILD */
    @GET
    @Path("/onetomanychild")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyChildren(@HeaderParam("Authorization") authHeader: String): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(exampleServiceClient.getOneToManyChildren()).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    @GET
    @Path("/onetomanychild/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyChildById(
            @HeaderParam("Authorization") authHeader: String,
            @PathParam("id") id: Long
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(exampleServiceClient.getOneToManyChildById(id)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }
}
