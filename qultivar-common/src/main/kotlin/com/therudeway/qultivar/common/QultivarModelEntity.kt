// QultivarModelEntity.kt
package com.therudeway.qultivar.common

import jakarta.json.bind.JsonbBuilder
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
open class QultivarModelEntity : QultivarEntity() {
    @Column(nullable = false)
    var createdDate: LocalDateTime? = null

    @Column(nullable = false)
    var updatedDate: LocalDateTime? = null

    @PrePersist
    fun prePersist() {
        createdDate = LocalDateTime.now()
        updatedDate = createdDate
    }

    @PreUpdate
    fun preUpdate() {
        updatedDate = LocalDateTime.now()
    }

    override fun toString(): String {
        val jsonb = JsonbBuilder.create()
        return jsonb.toJson(this)
    }
}
