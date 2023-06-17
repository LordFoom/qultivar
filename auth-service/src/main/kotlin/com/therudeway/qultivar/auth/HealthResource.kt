// HealthResource.kt
package com.therudeway.qultivar.auth

import com.therudeway.qultivar.common.QultivarHealthResource
import jakarta.ws.rs.Path

@Path("/health")
class HealthResource : QultivarHealthResource() {
}
