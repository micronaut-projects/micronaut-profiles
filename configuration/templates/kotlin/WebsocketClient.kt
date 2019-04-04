${packageName ? 'package ' + packageName : '' }

import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.*

@ClientWebSocket("/${propertyName}/{topic}/")
abstract class ${className} : AutoCloseable {

    var session: WebSocketSession? = null
        private set

    @OnOpen
    fun onOpen(session: WebSocketSession) {
        this.session = session
    }

    @OnMessage
    fun onMessage() {
    }
}