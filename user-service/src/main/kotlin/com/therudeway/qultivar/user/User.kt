// User.kt
package com.therudeway.qultivar.user

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import jakarta.json.bind.annotation.JsonbTransient

@Entity(name="user0")
class User(
    var name: String,
    var email: String,
    @field:JsonbTransient var password: String
) : PanacheEntity() {
    constructor() : this("", "", "")
}
