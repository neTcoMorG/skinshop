package mc.jun.skinshop.domain.chat.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Repository
public class ChatSessionRepository {

    private Map<String, SessionEntity> session;

    @PostConstruct
    private void init () {
        session = new HashMap<>();
    }

    public SessionEntity create (String userName, WebSocketSession socketSession) {
        SessionEntity createEntity = new SessionEntity(userName, socketSession);
        session.put(userName, createEntity);
        return createEntity;
    }

    public SessionEntity findByUserName (String userName) {
        if (exists(userName)) {
            return session.get(userName);
        }
        throw new IllegalStateException();
    }

    public void delete (String userName) {
        if (exists(userName)) {
            this.session.remove(userName);
        }
    }

     public boolean exists (String userName) {
        return this.session.containsKey(userName);
     }
}
