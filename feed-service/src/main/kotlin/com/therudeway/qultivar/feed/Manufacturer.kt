// Manufacturer.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarModelEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.json.bind.annotation.JsonbTransient
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class Manufacturer : QultivarModelEntity() {

    @Column(unique = true)
    lateinit var name: String

    @OneToMany(mappedBy = "manufacturer", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonbTransient
    var products: MutableList<Product> = mutableListOf()

}
