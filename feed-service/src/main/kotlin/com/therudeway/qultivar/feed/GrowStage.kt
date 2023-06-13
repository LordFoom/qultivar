// GrowStage.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id

@Entity
class GrowStage : PanacheEntityBase {
    @Id
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    lateinit var name: GrowStageEnum

    lateinit var description: String
}
