package teamkim.stream.domain.signaling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SignalingController {

    @MessageMapping("/offer/{camNum}")
    @SendTo("/topic/offer/{camNum}")
    public String handleOffer(@Payload String offer, @DestinationVariable(value = "camNum") String camNum) {
        log.info("[OFFER] cam{} : {}", camNum, offer);
        return offer;
    }

    @MessageMapping("/answer/{camNum}")
    @SendTo("/topic/answer/{camNum}")
    public String handleAnswer(@Payload String answer, @DestinationVariable(value = "camNum") String camNum) {
        log.info("[ANSWER] cam{} : {}", camNum, answer);
        return answer;
    }
}
