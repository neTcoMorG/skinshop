package mc.jun.skinshop.domain.chat.protocol;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Message {
    private MessageType messageType;
    private String target;
    private String self;
    private String message;
}
