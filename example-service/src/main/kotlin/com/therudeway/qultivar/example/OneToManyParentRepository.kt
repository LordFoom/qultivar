// OneToManyParentRepository.kt
package com.therudeway.qultivar.example

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class OneToManyParentRepository : PanacheRepository<OneToManyParent> {

    fun findByName(name: String) = find("name", name).firstResult()

}
