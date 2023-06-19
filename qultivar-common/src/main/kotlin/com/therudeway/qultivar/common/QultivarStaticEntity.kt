// QultivarStaticEntity.kt
package com.therudeway.qultivar.common

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.json.bind.JsonbBuilder
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.UniqueConstraint

@MappedSuperclass
open class QultivarStaticEntity : PanacheEntityBase {
    @Id var id: Long? = null

    @Column(unique = true)
    lateinit var name: String

    lateinit var description: String

    override fun toString(): String {
        val jsonb = JsonbBuilder.create()
        return jsonb.toJson(this)
    }
}
