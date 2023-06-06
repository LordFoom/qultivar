// UserServiceClient.kt
package com.therudeway.qultivar.api

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface UserServiceClient {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): Response

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): Response

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): Response

    @GET
    @Path("/user/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByEmail(@PathParam("email") email: String): Response

}
