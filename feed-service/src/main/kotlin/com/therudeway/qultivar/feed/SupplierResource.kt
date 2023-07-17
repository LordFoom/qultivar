// SupplierResource.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/feed/supplier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class SupplierResource : QultivarModelResource<Supplier, SupplierRepository>() {

    override fun getItemName(): String {
        return "Supplier"
    }

    override fun updateExistingItem(existingItem: Supplier, updatedItem: Supplier) {
        existingItem.name = updatedItem.name
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
