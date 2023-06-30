// QultivarEntity.kt
package com.therudeway.qultivar.common

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.json.bind.JsonbBuilder
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class QultivarEntity : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    override fun toString(): String {
        val jsonb = JsonbBuilder.create()
        return jsonb.toJson(this)
    }
}
