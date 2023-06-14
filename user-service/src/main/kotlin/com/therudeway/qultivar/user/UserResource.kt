// UserResource.kt
package com.therudeway.qultivar.user

import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/user")
class UserResource {
    protected val logger = LoggingUtils.logger<UserResource>()

    @Inject lateinit var userRepository: UserRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<User> {
        val users = userRepository.listAll()
        return users
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): User {
        val user = userRepository.findById(id)
        if (user != null) {
            return user
        }
        throw NotFoundException("User not found")
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByEmail(@PathParam("email") email: String): User {
        val user = userRepository.findByEmail(email)
        if (user != null) {
            return user
        }
        throw NotFoundException("User not found")
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun delete(@PathParam("id") id: Long) {
        val user = userRepository.findById(id)
        if (user != null) {
            user.delete()
        }
    }
}
