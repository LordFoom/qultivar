// GrowStageResource.kt
package com.therudeway.qultivar.feed

import com.google.gson.Gson
import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/feed/growstage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class GrowStageResource {
    private val logger = LoggingUtils.logger<GrowStageResource>()

    @Inject lateinit var growStageRepository: GrowStageRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<GrowStage> {
        val growstages = growStageRepository.listAll()
        return growstages
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): GrowStage {
        val growStage = growStageRepository.findById(id)
        if (growStage != null) {
            return growStage
        }
        throw NotFoundException("GrowStage not found")
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: GrowStageEnum): GrowStage {
        val growStage = growStageRepository.findByName(name)
        if (growStage != null) {
            return growStage
        }
        throw NotFoundException("GrowStage not found")
    }
}
