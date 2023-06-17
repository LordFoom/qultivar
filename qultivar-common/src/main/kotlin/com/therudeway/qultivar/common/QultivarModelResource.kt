// QultivarModelResource.kt
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
abstract class QultivarModelResource<E : QultivarModelEntity, R : PanacheRepository<E>> {
    protected val logger = LoggerFactory.getLogger(javaClass)

    @Inject lateinit protected var repository: R

    abstract fun getItemName(): String
    abstract fun updateExistingItem(existingItem: E, updatedItem: E)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<E> {
        val items = repository.listAll()
        return items
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun create(item: E): E {
        repository.persist(item)
        repository.flush()
        return item
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun update(@PathParam("id") id: Long, updatedItem: E): E {
        if (id != updatedItem.id) {
            throw BadRequestException("${getItemName()} ID mismatch")
        }

        val existingItem = repository.findById(id)
        if (existingItem == null) {
            throw NotFoundException("${getItemName()} not found")
        }

        updateExistingItem(existingItem, updatedItem)

        repository.persist(existingItem)
        return existingItem
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun delete(@PathParam("id") id: Long) {
        val item = repository.findById(id)
        if (item != null) {
            repository.delete(item)
        }
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
