package ev.aykhan.routers

import ev.aykhan.dto.LoginCredentialsDTO
import ev.aykhan.dto.RegisterCredentialsDTO
import ev.aykhan.plugins.TokenManager
import ev.aykhan.repositories.auth.AuthRepositoryImpl
import ev.aykhan.response.AuthResponse
import ev.aykhan.response.ErrorResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.generateAuthRoutes(manager: TokenManager) {

    route("/auth") {

        post("/login") {
            val credentials = call.receive<LoginCredentialsDTO>()
            try {
                val user = AuthRepositoryImpl.login(credentials)
                val token = manager.generateToken(user)
                call.respond(AuthResponse(token = token))
            } catch (e: NoSuchElementException) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("check parameters"))
            }
        }

        post("/register") {
            val credentials = call.receive<RegisterCredentialsDTO>()
            try {
                if (credentials.username.isNotBlank() && credentials.passValid()) {
                    val user = AuthRepositoryImpl.register(credentials)
                    val token = manager.generateToken(user)
                    call.respond(AuthResponse(token = token))
                } else call.respond(HttpStatusCode.BadRequest, ErrorResponse("check parameters"))
            } catch (e: IllegalStateException) {
                call.respond(HttpStatusCode.Forbidden, ErrorResponse(e.message.toString()))
            }
        }

    }

}