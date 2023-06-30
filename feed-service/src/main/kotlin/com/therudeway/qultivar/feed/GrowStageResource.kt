// GrowStageResource.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarStaticResource
import com.google.gson.Gson
import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/feed/growstage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class GrowStageResource : QultivarStaticResource<GrowStage, GrowStageRepository>() {

    override fun getItemName(): String {
        return "GrowStage"
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: GrowStageEnum): Response {
        val item = repository.findByName(name.toString())
        if (item != null) {
            return Response.ok(item).build()
        }
        return Response.status(Response.Status.NOT_FOUND).build()
    }
}
