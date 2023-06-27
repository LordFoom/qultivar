// AuthServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.QultivarHealthStatus
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface AuthServiceClient {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): QultivarHealthStatus

    @GET
    @Path("/auth/validateToken")
    @Produces(MediaType.APPLICATION_JSON)
    fun validateToken(@HeaderParam("Authorization") authHeader: String): Response
}
