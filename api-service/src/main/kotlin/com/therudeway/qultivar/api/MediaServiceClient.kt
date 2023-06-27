// MediaServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.QultivarHealthStatus
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface MediaServiceClient {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): QultivarHealthStatus

    @GET
    @Path("/media/{userId}/files")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMediaFiles(@PathParam("userId") userId: String): List<String>

    /*
    @POST
    @Path("/media/{userId}/upload/{fileName}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun uploadMedia(
            @PathParam("userId") userId: String,
            @PathParam("fileName") fileName: String,
            @FormParam("file") file: InputStream
    ): JsonObject
    */
}
