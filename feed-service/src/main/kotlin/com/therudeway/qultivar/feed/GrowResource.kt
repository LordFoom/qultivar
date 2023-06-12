// GrowResource.kt
package com.therudeway.qultivar.feed

import com.google.gson.Gson
import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/feed/grow")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class GrowResource {
    private val logger = LoggingUtils.logger<GrowResource>()

    @Inject lateinit var growRepository: GrowRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<Grow> {
        val grows = growRepository.listAll()
        return grows
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): Grow {
        val grow = growRepository.findById(id)
        if (grow != null) {
            return grow
        }
        throw NotFoundException("Grow not found")
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: String): Grow {
        val grow = growRepository.findByName(name)
        if (grow != null) {
            return grow
        }
        throw NotFoundException("Grow not found")
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByUserId(@PathParam("userId") userId: Long): List<Grow> {
        val grows = growRepository.listAllGrowsByUserId(userId)
        return grows
    }
}
