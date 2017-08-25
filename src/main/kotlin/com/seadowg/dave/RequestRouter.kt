package com.seadowg.dave

class RequestRouter(private val actionHandlers: Map<String, ActionHandler>) {
    fun route(request: Request): Response {
        return actionHandlers[request.action]!!.handle(request)
    }
}