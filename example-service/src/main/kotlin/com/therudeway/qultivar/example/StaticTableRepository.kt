// StaticTableRepository.kt
package com.therudeway.qultivar.example

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StaticTableRepository : PanacheRepository<StaticTable> {
    fun findByName(name: String) = find("name", name).firstResult()
}
