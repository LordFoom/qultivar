// HealthResource.kt
package com.therudeway.qultivar.example

import com.therudeway.qultivar.common.QultivarHealthResource
import jakarta.ws.rs.Path

@Path("/health")
class HealthResource : QultivarHealthResource() {
}
