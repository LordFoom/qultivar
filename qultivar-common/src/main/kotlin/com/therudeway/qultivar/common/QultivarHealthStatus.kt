// QultivarHealthStatus.kt
package com.therudeway.qultivar.common

import jakarta.json.bind.annotation.JsonbProperty

data class QultivarHealthStatus(
    @JsonbProperty("serviceName") val serviceName: String,
    @JsonbProperty("serviceRunning") val serviceRunning: Boolean,
    @JsonbProperty("errorMessage") val errorMessage: String?
)
