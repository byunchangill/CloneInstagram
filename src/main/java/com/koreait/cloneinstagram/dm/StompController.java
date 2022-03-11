package com.koreait.cloneinstagram.dm;

import com.koreait.cloneinstagram.dm.model.DmMsgDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompController {
    private final StompService service;
    private final SimpMessageSendingOperations msgSendingOperation;

    @MessageMapping("/msg")
    public void message(DmMsgDomain message) {
        service.insDmMsg(message);
        msgSendingOperation.convertAndSend("/sub/room/" + message.getIdm(), message);
    }
}
