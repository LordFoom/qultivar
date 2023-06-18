// FeedEventResource.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/feed/event")
class FeedEventResource : QultivarModelResource<FeedEvent, FeedEventRepository>() {

    override fun getItemName(): String {
        return "FeedEvent"
    }

    override fun updateExistingItem(existingItem: FeedEvent, updatedItem: FeedEvent) {
        existingItem.feedDate = updatedItem.feedDate
        existingItem.description = updatedItem.description
    }

    @GET
    @Path("/grow/{growId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByGrowId(@PathParam("growId") growId: Long): List<FeedEvent> {
        val feedEvents = repository.listAllFeedEventsByGrowId(growId)
        return feedEvents
    }
}
