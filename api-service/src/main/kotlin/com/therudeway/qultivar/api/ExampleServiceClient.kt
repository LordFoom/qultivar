// ExampleServiceClient.kt
package com.therudeway.qultivar.api

import com.therudeway.qultivar.common.QultivarHealthStatus
import com.therudeway.qultivar.example.StaticTable
import com.therudeway.qultivar.example.OneToManyParent
import com.therudeway.qultivar.example.OneToManyChild
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient
interface ExampleServiceClient {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    fun checkHealth(): QultivarHealthStatus


    /** STATIC TABLE */
    @GET
    @Path("/statictable")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStaticTables(): List<StaticTable>

    @GET
    @Path("/statictable/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStaticTableById(@PathParam("id") id: Long): StaticTable


    /** ONE TO MANY PARENT */
    @GET
    @Path("/onetomanyparent")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyParents(): List<OneToManyParent>

    @GET
    @Path("/onetomanyparent/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyParentById(@PathParam("id") id: Long): OneToManyParent


    /** ONE TO MANY CHILD */
    @GET
    @Path("/onetomanychild")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyChildren(): List<OneToManyChild>

    @GET
    @Path("/onetomanychild/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOneToManyChildById(@PathParam("id") id: Long): OneToManyChild


}
