// QultivarDTO.kt
package com.therudeway.qultivar.common

abstract class QultivarDTO<E: QultivarEntity>(open var id: Long? = null) {
    constructor(entity: E) : this(entity.id)

    abstract fun toEntity(vararg params: Any?): E
}
