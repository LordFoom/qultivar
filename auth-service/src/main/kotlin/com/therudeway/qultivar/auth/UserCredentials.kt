// UserCredentials.kt
package com.therudeway.qultivar.auth

data class UserCredentials(
    var id: Long = -1, 
    var email: String = "", 
    var password: String = ""
)