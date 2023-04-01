package mc.jun.skinshop.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.chat.protocol.Message;
import mc.jun.skinshop.domain.chat.repository.ChatSessionRepository;
import mc.jun.skinshop.domain.chat.repository.SessionEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatSessionRepository chatRepository;
    private final ObjectMapper objectMapper;

    public void create (String userName, WebSocketSession session) throws IOException {
        chatRepository.create(userName, session);
        session.sendMessage(new TextMessage("Hello, world " + userName));
    }

    public void send (Message message) throws IOException {
        SessionEntity findTarget = chatRepository.findByUserName(message.getTarget());
        findTarget.getSession().sendMessage(new TextMessage(message.getMessage()));
    }

    public void getAll (Message message) {

    }
}
