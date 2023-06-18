// Grow.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.json.bind.annotation.JsonbTransient
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class Grow : QultivarModelEntity() {

    lateinit var name: String

    lateinit var startDate: LocalDateTime
    
    var endDate: LocalDateTime? = null

    @Column(name = "user_id")
    var userId: Long = 0

    @OneToMany(mappedBy = "grow", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonbTransient
    var feedEvents: MutableList<FeedEvent> = mutableListOf()
}
