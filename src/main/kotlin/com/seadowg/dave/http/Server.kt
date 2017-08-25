package com.seadowg.dave.http

import com.seadowg.dave.ActionHandler
import com.seadowg.dave.RequestRouter
import com.seadowg.dave.json.Parser
import com.seadowg.dave.json.Serializer
import spark.kotlin.Http
import spark.kotlin.ignite

class Server {

    private var http: Http? = null

    fun serve(port: Int, actionHandlers: Map<String, ActionHandler>) {
        val spark = ignite().port(port)

        val requestRouter = RequestRouter(actionHandlers)
        val parser = Parser()
        val serializer = Serializer()

        spark.post("/") {
            val apiAiRequest = parser.parse(request.body())
            val apiAiResponse = requestRouter.route(apiAiRequest)

            response.header("Content-Type", "application/json")
            serializer.serialize(apiAiResponse)
        }

        http = spark
    }

    fun shutdown() {
        http?.stop()
    }
}