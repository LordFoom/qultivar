// FeedEventRepository.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FeedEventRepository : PanacheRepository<FeedEvent> {

    fun listAllFeedEventsByGrowId(growId: Long): List<FeedEvent> {
        return list("grow.id", growId)
    }
}
