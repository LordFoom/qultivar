// FeedEvent.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.time.LocalDateTime

@Entity
class FeedEvent : PanacheEntity() {
    lateinit var title: String
    lateinit var feedDate: LocalDateTime
    var userId: Long = 0
}
