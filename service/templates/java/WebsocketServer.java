${packageName ? 'package ' + packageName + ';' : ''}

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.*;

@ServerWebSocket("/${propertyName}/{topic}")
public class ${className} {
    private WebSocketBroadcaster broadcaster;

    public ${className}(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @OnOpen
    public void onOpen() {}

    @OnMessage
    public void onMessage() {}

    @OnClose
    public void onClose() {}

}