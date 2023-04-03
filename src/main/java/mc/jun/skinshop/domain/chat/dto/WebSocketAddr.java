package mc.jun.skinshop.domain.chat.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WebSocketAddr {
    private WebSocketSender sender;
    private WebSocketReceiver receiver;
}
