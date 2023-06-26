// FeedServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.QultivarHealthStatus
import com.therudeway.qultivar.feed.GrowStage
import com.therudeway.qultivar.feed.FeedEvent
import com.therudeway.qultivar.feed.Grow
import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.DELETE
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface FeedServiceClient {
    @GET @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): QultivarHealthStatus

    @GET
    @Path("/feed/growstage")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStages(): List<GrowStage>

    /***************************************************************************
        FEED EVENTS
    ***************************************************************************/
    @GET
    @Path("/feed/event/grow/{growId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedEventsByGrowId(@PathParam("growId") growId: Long): List<FeedEvent>

    @GET
    @Path("/feed/event/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedEventById(@PathParam("id") id: Long): FeedEvent

    @POST
    @Path("/feed/event")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun createFeedEvent(feedEvent: FeedEvent): FeedEvent

    @PUT
    @Path("/feed/event/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun updateFeedEvent(@PathParam("id") id: Long, feedEvent: FeedEvent): FeedEvent

    @DELETE
    @Path("/feed/event/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun deleteFeedEvent(@PathParam("id") id: Long)

    /***************************************************************************
        GROWS
    ***************************************************************************/
    @GET
    @Path("/feed/grow/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowsByUserId(@PathParam("userId") userId: Long): List<Grow>

    @GET
    @Path("/feed/grow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowById(@PathParam("id") id: Long): Grow

    @POST
    @Path("/feed/grow")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun createGrow(grow: Grow): Grow

    @PUT
    @Path("/feed/grow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun updateGrow(@PathParam("id") id: Long, grow: Grow): Grow

    @DELETE
    @Path("/feed/grow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun deleteGrow(@PathParam("id") id: Long)
}
