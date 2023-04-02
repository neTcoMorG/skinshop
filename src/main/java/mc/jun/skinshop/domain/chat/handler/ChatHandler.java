package mc.jun.skinshop.domain.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.chat.protocol.Message;
import mc.jun.skinshop.domain.chat.service.ChatService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage (WebSocketSession session, TextMessage message) throws Exception {
        Message raw = mappedToMessage(message);
        dispatcher(raw, session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session.getId() + " CONNECTED");
    }

    private void dispatcher (Message protocol, WebSocketSession session) throws IOException {
        switch (protocol.getMessageType()) {
            case JOIN -> chatService.create(protocol.getSelf(), session);
            case MESSAGE -> chatService.send(protocol);
            case LIST -> {
                Set<String> targets = chatService.getAll(protocol);
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(targets)));
            }
        }
    }

    private Message mappedToMessage (TextMessage message) throws Exception {
        return objectMapper.readValue(message.getPayload(), Message.class);
    }
}
