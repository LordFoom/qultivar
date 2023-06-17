// QultivarStaticResource.kt
package com.therudeway.qultivar.common

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.slf4j.LoggerFactory

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
abstract class QultivarStaticResource<E : QultivarStaticEntity, R : PanacheRepository<E>> {
    protected val logger = LoggerFactory.getLogger(javaClass)

    @Inject lateinit protected var repository: R

    abstract fun getItemName(): String

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<E> {
        val items = repository.listAll()
        return items
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): E {
        val item = repository.findById(id)
        if (item != null) {
            return item
        }
        throw NotFoundException("${getItemName()} not found")
    }
}
