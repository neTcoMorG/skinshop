package mc.jun.skinshop.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.chat.protocol.Message;
import mc.jun.skinshop.domain.chat.repository.ChatSessionRepository;
import mc.jun.skinshop.domain.chat.repository.SessionEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

@Slf4j
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
        SessionEntity sender = chatRepository.findByUserName(message.getSelf());

        updateTarget(sender, findTarget);
        findTarget.getSession().sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    }

    private void updateTarget (SessionEntity sender, SessionEntity target) {
        sender.getTargets().add(target.getName());
        target.getTargets().add(sender.getName());
    }

    public Set<String> getAll (Message message) {
        return chatRepository.findByUserName(message.getSelf()).getTargets();
    }
}
