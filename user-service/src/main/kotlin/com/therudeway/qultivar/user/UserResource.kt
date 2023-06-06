// UserResource.kt
package com.therudeway.qultivar.user

import com.therudeway.qultivar.common.LoggingUtils

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.NotAuthorizedException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/user")
class UserResource {
    protected val logger = LoggingUtils.logger<UserResource>()

    @Inject lateinit var userRepository: UserRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): Response {
        val users = userRepository.listAll()
        return Response.ok(users).build()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): Response 
    {
        val user = userRepository.findById(id)
        if (user != null) {
            return Response.ok(user).build()
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build()
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByEmail(@PathParam("email") email: String): Response
    {
        val user = userRepository.findByEmail(email)
        if (user != null) {
            return Response.ok(user).build()
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build()
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun delete(@PathParam("id") id: Long) : Response 
    {
        val user = userRepository.findById(id)
        if (user != null) {
            user.delete()
            return Response.status(Response.Status.OK).build()
        }
        return Response.status(Response.Status.NOT_FOUND).build()
    }
}
