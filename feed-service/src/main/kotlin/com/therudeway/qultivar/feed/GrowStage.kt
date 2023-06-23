// GrowStage.kt
package com.therudeway.qultivar.feed

import com.therudeway.qultivar.common.QultivarStaticEntity
import jakarta.persistence.Entity

@Entity
class GrowStage : QultivarStaticEntity() {
    var stageSequence: Int = 0
}
