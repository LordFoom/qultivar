// GrowResource.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/feed/grow")
class GrowResource : QultivarModelResource<Grow, GrowRepository>() {

    override fun getItemName(): String {
        return "FeedEvent"
    }

    override fun updateExistingItem(existingItem: Grow, updatedItem: Grow) {
        existingItem.name = updatedItem.name
        existingItem.startDate = updatedItem.startDate
        existingItem.endDate = updatedItem.endDate
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: String): Response {
        val item = repository.findByName(name)
        if (item != null) {
            return Response.ok(item).build()
        }
        return Response.status(Response.Status.NOT_FOUND).build()
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByUserId(@PathParam("userId") userId: Long): Response {
        val items = repository.listAllGrowsByUserId(userId)
        return Response.ok(items).build()
    }
}
