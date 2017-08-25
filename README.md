# dave

A small framework for building API.AI web fulfillment.

```kotlin
class MarcoPoloAction : ActionHandler {
    override fun handle(request: Request): Response {
        return when (request.params["speech"]) {
            "marco" -> Response("Polo!", emptyList())
            else -> Response("", emptyList())
        }
    }
}

class FallbackAction : ActionHandler {
    override fun handle(request: Request): Response {
        return Response("I'm not sure that's part of the game...", emptyList())
    }
}

fun main(args: Array<String>) {
    val server = Server()
    server.serve(8080, mapOf(
            "marco_polo" to MarcoPoloAction(),
            "input.unknown" to FallbackAction()
    ))
}
```