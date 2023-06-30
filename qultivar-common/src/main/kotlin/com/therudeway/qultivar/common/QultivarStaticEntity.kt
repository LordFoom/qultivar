// QultivarStaticEntity.kt
package com.therudeway.qultivar.common

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
open class QultivarStaticEntity : QultivarEntity() {
    @Id
    override var id: Long? = null

    @Column(unique = true)
    lateinit var name: String

    lateinit var description: String

}
