${packageName ? 'package ' + packageName : '' }

import io.micronaut.websocket.WebSocketBroadcaster
import io.micronaut.websocket.annotation.OnClose
import io.micronaut.websocket.annotation.OnMessage
import io.micronaut.websocket.annotation.OnOpen
import io.micronaut.websocket.annotation.ServerWebSocket

@ServerWebSocket("/${propertyName}/{topic}")
class ${className} {

    private WebSocketBroadcaster broadcaster

    ${className}(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster
    }

    @OnOpen
    void onOpen() {}

    @OnMessage
    void onMessage() {}

    @OnClose
    void onClose() {}
}