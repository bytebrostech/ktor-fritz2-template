package net.bytebros.fskotlin

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun Application.main() {
    install(ContentNegotiation) {
        gson()
    }
    routing {
        get {
            call.respondText("Hello from Ktor")
        }
    }
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = port) {
        main()
    }.start(wait = true)
}