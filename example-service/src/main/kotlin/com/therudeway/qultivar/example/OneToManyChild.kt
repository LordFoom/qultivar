// OneToManyChild.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarModelEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class OneToManyChild : QultivarModelEntity() {

    lateinit var randomDate: LocalDateTime
    lateinit var notes: String

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var oneToManyParent: OneToManyParent
}
