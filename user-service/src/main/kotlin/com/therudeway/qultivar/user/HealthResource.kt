// HealthResource.kt
package com.therudeway.qultivar.user

import com.therudeway.qultivar.common.QultivarHealthResource
import jakarta.ws.rs.Path

@Path("/health")
class HealthResource : QultivarHealthResource() {
}
