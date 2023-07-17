// SupplierProductResource.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelResource
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/feed/supplierproduct")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class SupplierProductResource : QultivarModelResource<SupplierProduct, SupplierProductRepository>() {

    override fun getItemName(): String {
        return "SupplierProduct"
    }

    override fun updateExistingItem(existingItem: SupplierProduct, updatedItem: SupplierProduct) {
        existingItem.supplier = updatedItem.supplier
        existingItem.product = updatedItem.product
    }

}
