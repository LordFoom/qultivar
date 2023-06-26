// StaticTable.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarStaticEntity
import jakarta.persistence.Entity

@Entity
class StaticTable : QultivarStaticEntity() {
    var stageSequence: Int? = null
}
