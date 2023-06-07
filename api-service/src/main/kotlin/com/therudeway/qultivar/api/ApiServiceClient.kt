// ApiServiceClient.kt
package com.therudeway.qultivar.api

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import com.therudeway.qultivar.common.HealthStatus

@RegisterRestClient
interface ApiServiceClient {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): HealthStatus

}
