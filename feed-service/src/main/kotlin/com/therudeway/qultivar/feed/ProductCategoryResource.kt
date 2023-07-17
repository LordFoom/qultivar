// ProductCategoryResource.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarStaticResource
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/feed/productcategory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ProductCategoryResource : QultivarStaticResource<ProductCategory, ProductCategoryRepository>() {

    override fun getItemName(): String {
        return "ProductCategory"
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: String): Response {
        val item = repository.findByName(name.toString())
        if (item != null) {
            return Response.ok(item).build()
        }
        return Response.status(Response.Status.NOT_FOUND).build()
    }

}
