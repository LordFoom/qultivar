// SupplierRepository.kt
package com.therudeway.qultivar.feed

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SupplierRepository : PanacheRepository<Supplier> {

    fun findByName(name: String) = find("name", name).firstResult()

}
