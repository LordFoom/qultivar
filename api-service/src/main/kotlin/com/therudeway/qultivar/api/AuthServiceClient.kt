// AuthServiceClient.kt
package com.therudeway.qultivar.api

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.HeaderParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import com.therudeway.qultivar.common.QultivarHealthStatus

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
