package com.therudeway.qultivar.auth

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.mindrot.jbcrypt.BCrypt

@ApplicationScoped
class UserService(@RestClient private val userServiceClient: UserServiceClient) {

    fun verifyCredentials(email: String, password: String): Boolean {
        val userCredentials = userServiceClient.getByEmail(email)
        if (userCredentials != null && BCrypt.checkpw(password, userCredentials.password)) {
            return true
        } else {
            return false
        }
    }
}
