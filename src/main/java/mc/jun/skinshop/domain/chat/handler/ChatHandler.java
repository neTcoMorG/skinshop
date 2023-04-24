package mc.jun.skinshop.domain.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.chat.dto.RawMessage;
import mc.jun.skinshop.domain.chat.dto.WebSocketAddr;
import mc.jun.skinshop.domain.chat.dto.WebSocketReceiver;
import mc.jun.skinshop.domain.chat.dto.WebSocketSender;
import mc.jun.skinshop.domain.chat.protocol.MessageType;
import mc.jun.skinshop.domain.chat.service.ChatService;
import mc.jun.skinshop.domain.dto.jwt.JwtUserObject;
import mc.jun.skinshop.domain.util.JwtProvider;
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
    private final JwtProvider jwtProvider;

    @Override
    protected void handleTextMessage (WebSocketSession session, TextMessage message) throws Exception {
        RawMessage rawMessage = mappedToRawMessage(message);
        JwtUserObject userObject = jwtProvider.parseToken(rawMessage.getToken());

        WebSocketSender webSocketSender = new WebSocketSender(userObject.getId(), userObject.getName(), userObject.getEmail(), session);
        WebSocketReceiver webSocketReceiver = new WebSocketReceiver(rawMessage.getTarget());
        WebSocketAddr webSocketAddr = new WebSocketAddr(webSocketSender, webSocketReceiver);
        
        dispatcher(rawMessage.getCommand(), webSocketAddr, rawMessage.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session.getId() + " CONNECTED");
    }

    private void dispatcher (MessageType command, WebSocketAddr addr, String payload) throws IOException {
        WebSocketSender sender = addr.getSender();
        WebSocketReceiver receiver = addr.getReceiver();

        switch (command) {
            case JOIN -> chatService.create(sender);
            case MESSAGE -> chatService.send(addr, payload);
            case LIST -> {
                Set<String> targets = chatService.getAll(sender);
                sender.getSession().sendMessage(new TextMessage(objectMapper.writeValueAsString(targets)));
            }
        }
    }

    private RawMessage mappedToRawMessage (TextMessage message) throws Exception {
        return objectMapper.readValue(message.getPayload(), RawMessage.class);
    }
}
