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
import jakarta.ws.rs.core.Response
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
    fun getAll(): Response {
        val items = repository.listAll()
        return Response.ok(items).build()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun create(item: E): Response {
        repository.persist(item)
        repository.flush()
        return Response.ok(Response.Status.CREATED).entity(item).build()
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun update(@PathParam("id") id: Long, updatedItem: E): Response {
        if (id != updatedItem.id) {
            throw BadRequestException("${getItemName()} ID mismatch")
        }

        val existingItem = repository.findById(id)
        if (existingItem == null) {
            throw NotFoundException("${getItemName()} not found")
        }

        updateExistingItem(existingItem, updatedItem)

        repository.persist(existingItem)
        return Response.ok(existingItem).build()
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun delete(@PathParam("id") id: Long): Response {
        val item = repository.findById(id)
        if (item != null) {
            repository.delete(item)
            return Response.noContent().build()
        }
        return Response.status(Response.Status.NOT_FOUND).build()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: Long): Response {
        val item = repository.findById(id)
        if (item != null) {
            return Response.ok(item).build()
        }
        return Response.status(Response.Status.NOT_FOUND).build()
    }
}
