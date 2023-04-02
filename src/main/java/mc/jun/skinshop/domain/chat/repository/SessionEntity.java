package mc.jun.skinshop.domain.chat.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionEntity {

    private String name;
    private WebSocketSession session;
    private Set<String> targets;

    public SessionEntity (String name, WebSocketSession session) {
        this.name = name;
        this.session = session;
        this.targets = new HashSet<>();
    }
}
