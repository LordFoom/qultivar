// FeedServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.QultivarHealthStatus
import com.therudeway.qultivar.feed.FeedEvent
import com.therudeway.qultivar.feed.Grow
import com.therudeway.qultivar.feed.GrowStage
import com.therudeway.qultivar.feed.Product
import com.therudeway.qultivar.feed.ProductCategory
import com.therudeway.qultivar.feed.Manufacturer
import jakarta.transaction.Transactional
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface FeedServiceClient {
    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): QultivarHealthStatus

    /** ************************************************************************* GROW STAGES */

    @GET
    @Path("/feed/growstage")
    @Produces(MediaType.APPLICATION_JSON)
    fun getGrowStages(): List<GrowStage>

    /** ************************************************************************* FEED EVENTS */
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

    /** ************************************************************************* GROWS */
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

    /** ************************************************************************* MANUFACTURER */
    @GET
    @Path("/feed/manufacturer/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getManufacturerByName(@PathParam("name") name: String): Manufacturer

    /** ************************************************************************* PRODUCT CATEGORIES */
    @GET
    @Path("/feed/productcategory/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getProductCategoryByName(@PathParam("name") name: String): ProductCategory

    /** ************************************************************************* PRODUCTS */
    @GET
    @Path("/feed/product")
    @Produces(MediaType.APPLICATION_JSON)
    fun getProducts(): List<Product>

    @GET
    @Path("/feed/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getProductById(@PathParam("id") id: Long): Product

    @POST
    @Path("/feed/product")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun createProduct(product: Product): Product

    @PUT
    @Path("/feed/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun updateProduct(@PathParam("id") id: Long, product: Product): Product

    @DELETE
    @Path("/feed/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun deleteProduct(@PathParam("id") id: Long)

}
