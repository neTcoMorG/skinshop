package mc.jun.skinshop.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.chat.dto.WebSocketAddr;
import mc.jun.skinshop.domain.chat.dto.WebSocketSender;
import mc.jun.skinshop.domain.chat.repository.ChatSessionRepository;
import mc.jun.skinshop.domain.chat.repository.SessionEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatSessionRepository chatSessionRepository;
    private final ObjectMapper objectMapper;

    public void create (WebSocketSender sender) throws IOException {
        log.info("Created Session Entity: " + sender.getName());

        if (!chatSessionRepository.exists(sender.getName())) {
            chatSessionRepository.create(sender.getName(), sender.getSession());
            sender.getSession().sendMessage(new TextMessage("Hello, world " + sender.getName()));
            return;
        }

        sender.getSession().sendMessage(new TextMessage("이미 존재하는 세션"));
    }

    public void send (WebSocketAddr addr, String payload) throws IOException {
        SessionEntity findTarget = chatSessionRepository.findByUserName(
                addr.getReceiver().getName());
        SessionEntity findSender = chatSessionRepository.findByUserName(
                addr.getSender().getName());

        updateEntity(findSender, findTarget);
        findTarget.getSession().sendMessage(new TextMessage(objectMapper.writeValueAsString(payload)));
    }

    private void updateEntity (SessionEntity sender, SessionEntity target) {
        sender.getTargets().add(target.getName());
        target.getTargets().add(sender.getName());
    }

    public Set<String> getAll (WebSocketSender sender) {
        return chatSessionRepository.findByUserName(sender.getName()).getTargets();
    }
}
