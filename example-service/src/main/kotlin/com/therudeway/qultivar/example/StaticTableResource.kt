// StaticTableResource.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarStaticResource
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/example/statictable")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class StaticTableResource : QultivarStaticResource<StaticTable, StaticTableRepository>() {

    override fun getItemName(): String {
        return "StaticTable"
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: StaticTableEnum): StaticTable {
        val item = repository.findByName(name.toString())
        if (item != null) {
            return item
        }
        throw NotFoundException("${getItemName()} not found")
    }
}
