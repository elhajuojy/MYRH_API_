package ma.yc.api.myrhapi.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.w3c.dom.Text;


public class WebSocketHandler extends TextWebSocketHandler {
    private String key = "myrhapi";
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("New Message Received" + message.getPayload());

        session.sendMessage(new TextMessage("Received :  "+ message.getPayload() + " key " +this.key + " !"));

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New Connection Established");
        TextMessage textMessage = new TextMessage("Welcome to the chat");
        session.sendMessage(textMessage);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection Closed");
        super.afterConnectionClosed(session, status);
    }
}
