package mc.jun.skinshop.domain.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.chat.protocol.Message;
import mc.jun.skinshop.domain.chat.protocol.MessageType;
import mc.jun.skinshop.domain.chat.service.ChatService;
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
    private final ChatService chatService;

    @Override
    protected void handleTextMessage (WebSocketSession session, TextMessage message) throws Exception {
        Message raw = objectMapper.readValue(message.getPayload(), Message.class);

        if (IS_JOIN_PROTOCOL(raw)) {
            chatService.create(raw.getSelf(), session);
            return;
        }

        if (IS_MESSAGE_PROTOCOL(raw)) {
            chatService.send(raw);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("JOIN: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("QUIT: " + session.getRemoteAddress().getAddress().toString());
    }

    private boolean IS_JOIN_PROTOCOL (Message message) {
        return message.getMessageType().equals(MessageType.JOIN);
    }

    private boolean IS_MESSAGE_PROTOCOL (Message message) {
        return message.getMessageType().equals(MessageType.MESSAGE);
    }
}
