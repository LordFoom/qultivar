// FeedServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.HealthStatus
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
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface FeedServiceClient {

    @GET @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): HealthStatus

    @GET
    @Path("/feed/growstage")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStages(): List<GrowStage>

    @GET
    @Path("/feed/growstage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStageById(@PathParam("id") id: Long): GrowStage

    @GET
    @Path("/feed/growstage/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStageByName(@PathParam("name") name: String): GrowStage

    @GET
    @Path("/feed/event")
    @Produces(MediaType.APPLICATION_JSON)
    fun getFeedEvents(): List<FeedEvent>

    @GET
    @Path("/feed/grow")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrows(): List<Grow>

    @GET
    @Path("/feed/grow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowById(@PathParam("id") id: Long): Grow

    @POST
    @Path("/feed/grow")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun createGrow(grow: Grow): Grow

    @PUT
    @Path("/feed/grow/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun updateGrow(@PathParam("id") id: Long, grow: Grow): Grow

    @GET
    @Path("/feed/grow/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowsByUserId(@PathParam("userId") userId: Long): List<Grow>
}
