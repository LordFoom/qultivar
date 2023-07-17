// ProductCategory.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarStaticEntity
import jakarta.persistence.Entity
import jakarta.json.bind.annotation.JsonbTransient
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
class ProductCategory : QultivarStaticEntity() {

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonbTransient
    var products: MutableList<Product> = mutableListOf()

}
