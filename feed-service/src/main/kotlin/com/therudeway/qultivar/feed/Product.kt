// Product.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelEntity
import jakarta.persistence.Entity
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
class Product : QultivarModelEntity() {

    lateinit var name: String
    lateinit var description: String

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    var category: ProductCategory? = null

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var manufacturer: Manufacturer

}
