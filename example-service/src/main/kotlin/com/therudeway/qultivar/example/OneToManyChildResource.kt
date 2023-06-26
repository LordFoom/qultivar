// OneToManyChildResource.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/example/onetomanychild")
class OneToManyChildResource : QultivarModelResource<OneToManyChild, OneToManyChildRepository>() {

    override fun getItemName(): String {
        return "OneToManyChild"
    }

    override fun updateExistingItem(existingItem: OneToManyChild, updatedItem: OneToManyChild) {
        existingItem.randomDate = updatedItem.randomDate
        existingItem.notes = updatedItem.notes
    }

    @GET
    @Path("/onetomanyparent/{oneToManyParentId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByParentId(@PathParam("oneToManyParentId") oneToManyParentId: Long): List<OneToManyChild> {
        val oneToManyChildren = repository.listAllOneToManyChildrenByOneToManyParentId(oneToManyParentId)
        return oneToManyChildren
    }
}
