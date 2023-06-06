// TokenUtils.kt
package com.therudeway.qultivar.auth

import com.therudeway.qultivar.common.LoggingUtils
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.mindrot.jbcrypt.BCrypt

@Singleton
class TokenUtils
@Inject
constructor(
        @ConfigProperty(name = "qultivar.jwt.duration") private val jwtDuration: Long,
        @ConfigProperty(name = "qultivar.jwt.secret") private val jwtSecret: String
) {
    private val logger = LoggingUtils.logger<TokenUtils>()

    private val BLACKLISTED_TOKENS = HashSet<String>()
    private val ISSUED_TOKENS = HashSet<String>()

    fun generateAccessToken(userEmail: String): String {
        val now = Instant.now()
        val expiryDate = now.plus(jwtDuration, ChronoUnit.SECONDS)
        val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        val token =
                Jwts.builder()
                        .setSubject(userEmail)
                        .setIssuedAt(Date.from(now))
                        .setExpiration(Date.from(expiryDate))
                        .signWith(key)
                        .compact()

        ISSUED_TOKENS.add(token)
        return token
    }

    fun invalidateToken(token: String) {
        if (!BLACKLISTED_TOKENS.contains(token)) {
            BLACKLISTED_TOKENS.add(token)
        }
        if (ISSUED_TOKENS.contains(token)) {
            ISSUED_TOKENS.remove(token)
        }
    }

    fun validateToken(token: String): Boolean {
        if (BLACKLISTED_TOKENS.contains(token)) {
            logger.info("token has been blacklisted")
            return false
        }
        if (tokenHasExpired(token)) {
            logger.info("token has expired")
            return false
        }
        return true
    }

    fun tokenHasExpired(token: String): Boolean {
        try {
            val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
            val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
            val expiration = claims.expiration.toInstant().toEpochMilli()
            val current = Instant.now().toEpochMilli()

            return (expiration < current)
            
        } catch (e: Exception) {
            logger.error(e.message)
            return false
        }
    }

    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
}
