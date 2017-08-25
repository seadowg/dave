package com.seadowg.dave.test

import com.seadowg.dave.ActionHandler
import com.seadowg.dave.Request
import com.seadowg.dave.Response
import com.seadowg.dave.http.Server

class MarcoPoloAction : ActionHandler {
    override fun handle(request: Request): Response {
        return when (request.params["speech"]) {
            "marco" -> Response("Polo!")
            else -> Response("", emptyList())
        }
    }
}

class FallbackAction : ActionHandler {
    override fun handle(request: Request): Response {
        return Response("I'm not sure that's part of the game...")
    }
}

fun main(args: Array<String>) {
    val server = Server()
    server.serve(8080, mapOf(
            "marco_polo" to MarcoPoloAction(),
            "input.unknown" to FallbackAction()
    ))
}



