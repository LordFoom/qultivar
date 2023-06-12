// FeedEventRepository.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FeedEventRepository : PanacheRepository<FeedEvent> {

    fun listAllFeedEventsByUserId(userId: Long): List<FeedEvent> {
        return list("userId", userId)
    }

}