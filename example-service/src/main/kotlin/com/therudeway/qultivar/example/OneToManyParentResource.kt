// OneToManyParentResource.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/example/onetomanyparent")
class OneToManyParentResource : QultivarModelResource<OneToManyParent, OneToManyParentRepository>() {

    override fun getItemName(): String {
        return "OneToManyParent"
    }

    override fun updateExistingItem(existingItem: OneToManyParent, updatedItem: OneToManyParent) {
        existingItem.name = updatedItem.name
        existingItem.description = updatedItem.description
        existingItem.firstDate = updatedItem.firstDate
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: String): OneToManyParent {
        val item = repository.findByName(name)
        if (item != null) {
            return item
        }
        throw NotFoundException("${getItemName()} not found")
    }

}
