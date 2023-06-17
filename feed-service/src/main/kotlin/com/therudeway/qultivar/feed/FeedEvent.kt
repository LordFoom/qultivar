// FeedEvent.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class FeedEvent : QultivarModelEntity() {

    lateinit var name: String
    lateinit var feedDate: LocalDateTime

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var grow: Grow
}
