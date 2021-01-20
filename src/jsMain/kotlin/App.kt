package net.bytebros.fskotlin

import dev.fritz2.dom.append
import dev.fritz2.dom.html.renderElement

val app = renderElement {
    div {
        +"Hello from Fritz2!"
    }
}

fun main() {
    append("target", app)
}