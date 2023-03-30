package mc.jun.skinshop.domain.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.chat.dto.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage (WebSocketSession session, TextMessage message) throws Exception {
        Message recv = objectMapper.readValue(message.getPayload(), Message.class);
        session.sendMessage(new TextMessage(recv.getType().toString()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("JOIN: " + session.getRemoteAddress().getAddress().toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("QUIT: " + session.getRemoteAddress().getAddress().toString());
    }
}
