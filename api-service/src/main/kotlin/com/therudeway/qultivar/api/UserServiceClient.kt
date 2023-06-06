// UserServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.HealthStatus
import com.therudeway.qultivar.user.User
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface UserServiceClient {

    @GET @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): HealthStatus

    @GET @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsers(): List<User>

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserById(@PathParam("id") id: Long): User

    @GET
    @Path("/user/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserByEmail(@PathParam("email") email: String): User

}
