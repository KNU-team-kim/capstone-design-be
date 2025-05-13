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

    @MessageMapping("/offer")
    @SendTo("/topic/offer")
    public String handleOffer(@Payload String offer) {
        log.info("[OFFER] {}", offer);
        return offer;
    }

    @MessageMapping("/answer/{client-id}/{direction}")
    @SendTo("/topic/answer/{client-id}/{direction}")
    public String handleAnswer(@Payload String answer, @DestinationVariable(value = "client-id") String clientId) {
        log.info("[ANSWER] client-({}) : {}", clientId, answer);
        return answer;
    }

    @MessageMapping("/iceCandidate/{cam-num}")
    @SendTo("/topic/iceCandidate/{cam-num}")
    public String handleIceCandidate(@Payload String iceCandidate, @DestinationVariable(value = "cam-num") String camNum) {
        log.info("[ICECANDIDATE] cam-({}) : {}", camNum, iceCandidate);
        return iceCandidate;
    }
}