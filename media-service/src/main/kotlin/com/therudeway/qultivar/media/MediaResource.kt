// Mediaresource.kt
package com.therudeway.qultivar.media

import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.json.Json
import jakarta.json.JsonObject
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.FormParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.InternalServerErrorException
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import org.eclipse.microprofile.config.inject.ConfigProperty

@Path("/media/{userId}")
class MediaResource(
        @ConfigProperty(name = "qultivar.media.file.location") private val mediaRoot: String
) {
    private val logger = LoggingUtils.logger<MediaResource>()

    @Inject lateinit var mediaUtils: MediaUtils

    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMediaFiles(@PathParam("userId") userId: String): List<String> {
        try {
            val dir = File("$mediaRoot/$userId")

            // Check if the directory exists
            if (!dir.exists() || !dir.isDirectory) {
                throw NotFoundException("No files found")
            }

            // Get the list of files in the directory
            val files = dir.listFiles()

            // Create a list of MediaFile objects
            return files.map { file -> file.name }
        } catch (e: NotFoundException) {
            throw e
        } catch (e: Exception) {
            throw InternalServerErrorException("Failed to get media files")
        }
    }

    @POST
    @Path("/upload/{fileName}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun uploadMedia (
            @PathParam("userId") userId: String,
            @PathParam("fileName") fileName: String,
            @FormParam("file") file: InputStream
    ): JsonObject {
        logger.info("MediaResource.uploadMedia() called")
        // Verify that the uploaded file is an image or video file
        val fileContent = mediaUtils.toByteArray(file)
        val mimeType = mediaUtils.detectMimeType(fileContent)
        if (!mediaUtils.isImageOrVideoMimeType(mimeType)) {
            throw BadRequestException("Invalid file type: $mimeType")
        }

        // Save file to disk
        val dir = Paths.get(mediaRoot, userId)
        if (!Files.exists(dir)) {
            Files.createDirectories(dir)
        }
        val filePath = dir.resolve(fileName)
        Files.write(filePath, fileContent)

        // TODO: Generate and save thumbnail for image files

        // Return a JSON object indicating success
        return Json.createObjectBuilder()
            .add("userId", userId)
            .add("fileName", fileName)
            .build()
    }

    @DELETE
    @Path("/delete/{fileName}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteMedia(
            @PathParam("userId") userId: String,
            @PathParam("fileName") fileName: String
    ): Response {
        val file = File("$mediaRoot/$userId/$fileName")

        // Check if the file exists
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("File not found: $fileName")
                    .build()
        }

        // Delete the file from the mounted drive
        file.delete()

        // Return a response indicating success
        return Response.ok()
                .entity(
                        Json.createObjectBuilder()
                                .add("userId", userId)
                                .add("fileName", fileName)
                                .build()
                )
                .build()
    }

    @GET
    @Path("/download/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    fun downloadMedia(
            @PathParam("userId") userId: String,
            @PathParam("fileName") fileName: String
    ): Response {
        val file = File("$mediaRoot/$userId/$fileName")

        // Check if the file exists
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("File not found: $fileName")
                    .build()
        }

        // Read the file from the mounted drive
        val data = Files.readAllBytes(Paths.get(mediaRoot, userId, fileName))

        // Return the file as a stream
        return Response.ok(data)
                .header("Content-Disposition", "attachment; filename=\"$fileName\"")
                .build()
    }
}
