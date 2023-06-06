// User.kt
package com.therudeway.qultivar.user

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity

@Entity(name="user0")
class User (
    var name: String,
    var email: String,
    var password: String
) : PanacheEntity() {
    constructor() : this("", "", "")
}
