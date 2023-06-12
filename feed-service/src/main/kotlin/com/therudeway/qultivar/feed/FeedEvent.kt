// FeedEvent.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class FeedEvent : PanacheEntity() {
    lateinit var name: String
    lateinit var feedDate: LocalDateTime

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var grow: Grow
}
