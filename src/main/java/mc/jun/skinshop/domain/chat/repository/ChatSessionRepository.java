package mc.jun.skinshop.domain.chat.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Repository
public class ChatSessionRepository {

    private Map<String, WebSocketSession> session;

    @PostConstruct
    private void init () {
        session = new HashMap<>();
    }

    public WebSocketSession create (String userName, WebSocketSession socketSession) {
        session.put(userName, socketSession);
        return socketSession;
    }

    public WebSocketSession findByUserName (String userName) {
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
