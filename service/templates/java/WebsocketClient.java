${packageName ? 'package ' + packageName + ';' : ''}

import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.*;

@ClientWebSocket("/${propertyName}/{topic}/")
public abstract class ${className} implements AutoCloseable {

    private WebSocketSession session;

    @OnOpen
    public void onOpen(WebSocketSession session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage() {}

    public WebSocketSession getSession() {
        return session;
    }
}