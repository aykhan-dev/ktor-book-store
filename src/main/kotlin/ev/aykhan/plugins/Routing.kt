package ev.aykhan.plugins

import com.typesafe.config.ConfigFactory
import ev.aykhan.routers.generateAuthRoutes
import ev.aykhan.routers.generateBookRoutes
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.routing.*

fun Application.configureRouting() {

    val config = HoconApplicationConfig(ConfigFactory.load())
    val tokenManager = TokenManager(config)

    routing {
        generateAuthRoutes(tokenManager)
        generateBookRoutes()
    }

}
