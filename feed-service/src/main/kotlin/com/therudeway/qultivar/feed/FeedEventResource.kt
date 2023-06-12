// FeedEventResource.kt
package com.therudeway.qultivar.feed

import com.google.gson.Gson
import com.therudeway.qultivar.common.LoggingUtils
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/feed/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FeedEventResource {
    private val logger = LoggingUtils.logger<FeedEventResource>()

    @Inject lateinit var feedEventRepository: FeedEventRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<FeedEvent> {
        val feedEvents = feedEventRepository.listAll()
        return feedEvents
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): FeedEvent {
        val feedEvent = feedEventRepository.findById(id)
        if (feedEvent != null) {
            return feedEvent
        }
        throw NotFoundException("FeedEvent not found")
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByUserId(@PathParam("userId") userId: Long): List<FeedEvent> {
        val feedEventList = feedEventRepository.listAllFeedEventsByUserId(userId)
        return feedEventList
    }
}
