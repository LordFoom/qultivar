// GrowStage.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id

@Entity
class GrowStage : PanacheEntity() {
    @Enumerated(EnumType.STRING)
    lateinit var name: GrowStageEnum

    lateinit var description: String
}
