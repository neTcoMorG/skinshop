package mc.jun.skinshop.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ChatRoom {

    private Set<WebSocketSession> sessions;
    private String uuid;
    private String name;

    public ChatRoom (String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        sessions = new HashSet<>();
    }

    public void join (WebSocketSession webSocketSession) {
        this.sessions.add(webSocketSession);
    }
}
