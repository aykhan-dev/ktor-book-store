package ev.aykhan

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ev.aykhan.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSecurity()
        configureRouting()
        configureSerialization()
        configureMonitoring()
    }.start(wait = true)
}
