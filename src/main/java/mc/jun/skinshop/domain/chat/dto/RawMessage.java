package mc.jun.skinshop.domain.chat.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.chat.protocol.MessageType;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RawMessage {
    private MessageType command;
    private String token;
    private String target;
    private String payload;
}
