// StaticTable.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarStaticEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class StaticTable : QultivarStaticEntity() {
    @Column(nullable = false) var rowSequence: Int? = 0
}
