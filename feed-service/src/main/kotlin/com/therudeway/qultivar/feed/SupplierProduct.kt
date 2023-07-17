// SupplierProduct.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelEntity
import jakarta.json.bind.annotation.JsonbTransient
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["supplier_id", "product_id"])])
class SupplierProduct : QultivarModelEntity() {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var supplier: Supplier

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var product: Product

}
