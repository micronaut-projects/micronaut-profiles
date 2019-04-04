${packageName ? 'package ' + packageName : '' }

import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.*

@ClientWebSocket("/${propertyName}/{topic}/")
abstract class ${className} implements AutoCloseable {

    private WebSocketSession session

    @OnOpen
    void onOpen(WebSocketSession session) {
        this.session = session
    }

    @OnMessage
    void onMessage() {}

}