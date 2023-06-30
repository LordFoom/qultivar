// FeedEventDTO.kt
package com.therudeway.qultivar.api.dto

import com.therudeway.qultivar.feed.FeedEvent
import com.therudeway.qultivar.feed.Grow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class FeedEventDTO(
    var id: Long? = null,
    var feedDate: String = "",
    var description: String = "",
    var growId: Long = 0L
) {
    constructor(feedEvent: FeedEvent) : this(
        feedEvent.id,
        feedEvent.feedDate.format(DateTimeFormatter.ISO_DATE_TIME),
        feedEvent.description,
        feedEvent.grow.id ?: throw IllegalArgumentException("growId cannot be null")
    )

    fun toEntity(grow: Grow): FeedEvent {
        val entity = FeedEvent()
        entity.id = this.id
        entity.feedDate = LocalDateTime.parse(this.feedDate, DateTimeFormatter.ISO_DATE_TIME)
        entity.description = this.description
        entity.grow = grow
        return entity
    }
}
