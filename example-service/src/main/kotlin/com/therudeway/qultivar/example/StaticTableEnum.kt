// StaticTableEnum.kt
package com.therudeway.qultivar.example

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class StaticTableEnum(val id: Int) {
    Row1(1),
    Row2(2),
    Row3(3);
}
