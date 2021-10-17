package ev.aykhan.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import ev.aykhan.entity.User
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.config.*
import java.util.*

class TokenManager(config: HoconApplicationConfig) {

    val audience = config.property("audience").getString()
    val issuer = config.property("issuer").getString()
    val secret = config.property("secret").getString()
    val realm = config.property("realm").getString()

    fun generateToken(user: User): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("id", user.id)
            .withClaim("username", user.username)
            .withClaim("activeToken", user.activeToken)
            .withExpiresAt(Date(System.currentTimeMillis() + 60_000L * 60))
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }

}

fun Application.configureSecurity() {

    val config = HoconApplicationConfig(ConfigFactory.load())
    val manager = TokenManager(config)

    authentication {
        jwt {
            this.realm = manager.realm
            verifier(manager.verifyToken())
            validate { credential ->
                if (credential.payload.audience.contains(manager.audience)) JWTPrincipal(credential.payload) else null
            }
        }
    }

}