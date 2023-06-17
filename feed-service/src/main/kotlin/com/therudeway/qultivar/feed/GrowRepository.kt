// GrowRepository.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GrowRepository : PanacheRepository<Grow> {

    fun findByName(name: String) = find("name", name).firstResult()

    fun listAllGrowsByUserId(userId: Long): List<Grow> {
        return list("userId", userId)
    }
}
