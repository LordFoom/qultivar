// UserResource.kt
package com.therudeway.qultivar.user

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

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
    fun getByEmail(@PathParam("email") email: String): User {
        val user = repository.findByEmail(email)
        if (user != null) {
            return user
        }
        throw NotFoundException("${getItemName()} not found")
    }
}
