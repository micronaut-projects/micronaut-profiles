${packageName ? 'package ' + packageName : '' }

import io.micronaut.websocket.WebSocketBroadcaster
import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.*

@ServerWebSocket("/${propertyName}/{topic}")
class ${className}(private val broadcaster: WebSocketBroadcaster) {

    @OnOpen
    fun onOpen() {
    }

    @OnMessage
    fun onMessage() {
    }

    @OnClose
    fun onClose() {
    }

}