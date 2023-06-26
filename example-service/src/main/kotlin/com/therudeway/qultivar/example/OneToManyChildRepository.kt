// OneToManyChildRepository.kt
package com.therudeway.qultivar.example

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class OneToManyChildRepository : PanacheRepository<OneToManyChild> {
    fun listAllOneToManyChildrenByOneToManyParentId(oneToManyParentId: Long): List<OneToManyChild> {
        return list("oneToManyParent.id", oneToManyParentId)
    }
}
