// OneToManyParent.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarModelEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.json.bind.JsonbBuilder
import jakarta.json.bind.annotation.JsonbTransient
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class OneToManyParent : QultivarModelEntity() {

    @Column(unique = true)
    lateinit var name: String

    lateinit var description: String

    lateinit var firstDate: LocalDateTime
    
    @OneToMany(mappedBy = "oneToManyParent", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonbTransient
    var oneToManyChildren: MutableList<OneToManyChild> = mutableListOf()
}
