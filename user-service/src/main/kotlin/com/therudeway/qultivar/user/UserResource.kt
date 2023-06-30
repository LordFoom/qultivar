// UserResource.kt
package com.therudeway.qultivar.user

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/user")
class UserResource : QultivarModelResource<User, UserRepository>() {

    override fun getItemName(): String {
        return "User"
    }

    override fun updateExistingItem(existingItem: User, updatedItem: User) {
        existingItem.name = updatedItem.name
        existingItem.email = updatedItem.email
        existingItem.password = updatedItem.password
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByEmail(@PathParam("email") email: String): Response {
        val item = repository.findByEmail(email)
        if (item != null) {
            return Response.ok(item).build()
        }
        return Response.status(Response.Status.NOT_FOUND).build()
    }
}
