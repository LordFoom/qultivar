// AuthResource.kt
package com.therudeway.qultivar.auth

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.HeaderParam
import com.therudeway.qultivar.auth.UserService

@Path("/auth")
class AuthResource(private val userService: UserService) {

    @Inject lateinit var tokenUtils: TokenUtils

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun login(credentials: UserCredentials): Response {
        val verifiedUser = userService.verifyCredentials(credentials.email, credentials.password)
        if (verifiedUser) {
            val token = tokenUtils.generateAccessToken(credentials.email)
            return Response.ok(mapOf("token" to token)).build()
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build()
        }
    }

    @POST
    @Path("/logout")
    fun logout(@HeaderParam("Authorization") token: String): Response {
        if (token.startsWith("Bearer ")) {
            val accessToken = token.substring("Bearer ".length).trim()
            tokenUtils.invalidateToken(accessToken)
        }
        return Response.noContent().build()
    }

    @GET
    @Path("/validateToken")
    @Produces(MediaType.APPLICATION_JSON)
    fun validateToken(@HeaderParam("Authorization") authHeader: String): Response {
        if (authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring("Bearer ".length).trim()
            val isValidToken = tokenUtils.validateToken(token)
            if (isValidToken) {
                return Response.ok().build()
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build()
    }
}
