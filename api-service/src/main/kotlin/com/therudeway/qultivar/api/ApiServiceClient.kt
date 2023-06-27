// ApiServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.QultivarHealthStatus
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface ApiServiceClient {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): QultivarHealthStatus
}
