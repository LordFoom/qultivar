// User.kt
package com.therudeway.qultivar.user

import com.therudeway.qultivar.common.QultivarModelEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "user0")
class User : QultivarModelEntity() {

    @Column(unique = true)
    lateinit var name: String

    @Column(unique = true)
    lateinit var email: String

    lateinit var password: String
}
