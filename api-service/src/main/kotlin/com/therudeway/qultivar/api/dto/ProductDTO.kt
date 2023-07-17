// ProductDTO.kt
package com.therudeway.qultivar.api.dto

import com.therudeway.qultivar.common.QultivarDTO

import com.therudeway.qultivar.feed.Manufacturer
import com.therudeway.qultivar.feed.Product
import com.therudeway.qultivar.feed.ProductCategory

data class ProductDTO(
    override var id: Long? = null,
    var name: String = "",
    var description: String = "",
    var manufacturer: String = "",
    var category: String = ""
) : QultivarDTO<Product>() {

    constructor(product: Product) : this(
        product.id,
        product.name,
        product.description,
        if (product.manufacturer.name.isNullOrBlank()) {
            throw IllegalArgumentException("Product manufacturer cannot be null or empty")
        } else {
            product.manufacturer.name
        },
        if (product.category?.name.isNullOrBlank()) {
            throw IllegalArgumentException("Product category name cannot be null or empty")
        } else {
            product.category?.name ?: ""
        }
    )

    override fun toEntity(vararg params: Any?): Product {
        val manufacturer = params.getOrNull(0) as? Manufacturer
            ?: throw IllegalArgumentException("The first parameter must be a ProductCategory object.")
    
        val productCategory = params.getOrNull(1) as? ProductCategory
            ?: throw IllegalArgumentException("The second parameter must be a ProductCategory object.")
        
        val entity = Product()
        entity.id = this.id
        entity.name = this.name
        entity.description = this.description
        entity.manufacturer = manufacturer
        entity.category = productCategory
        return entity
    }
}