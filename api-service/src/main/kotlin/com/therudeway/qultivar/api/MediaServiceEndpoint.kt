// MediaServiceEndpoint.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.FormParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import java.io.InputStream
import org.eclipse.microprofile.rest.client.inject.RestClient

@Path("/api")
public class MediaServiceEndpoint {
    private val logger = LoggingUtils.logger<MediaServiceEndpoint>()

    @Inject @RestClient lateinit var authServiceClient: AuthServiceClient
    @Inject @RestClient lateinit var mediaServiceClient: MediaServiceClient

    @GET
    @Path("/media/{userId}/files")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMediaFiles(
            @HeaderParam("Authorization") authHeader: String,
            @PathParam("userId") userId: String
    ): Response {
        try {
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(mediaServiceClient.getMediaFiles(userId)).build()
        } catch (e: NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.message).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }

    /*
    @POST
    @Path("/media/{userId}/upload/{fileName}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun uploadMedia(
            @HeaderParam("Authorization") authHeader: String,
            @PathParam("userId") userId: String,
            @PathParam("fileName") fileName: String,
            @FormParam("file") file: InputStream
    ): Response {
        try {
            logger.info("MediaServiceEndpoint upload")
            val response = authServiceClient.validateToken(authHeader)
            if (response.status != 200) {
                return Response.status(Response.Status.UNAUTHORIZED).build()
            }
            return Response.ok(mediaServiceClient.uploadMedia(userId, fileName, file)).build()
        } catch (e: Exception) {
            return Response.serverError().entity(e.message).build()
        }
    }
    */
}
