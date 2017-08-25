package com.seadowg.dave

interface ActionHandler {
    fun handle(request: Request): Response
}
