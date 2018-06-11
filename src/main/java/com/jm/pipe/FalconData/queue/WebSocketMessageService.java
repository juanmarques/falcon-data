package com.jm.pipe.FalconData.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jm.pipe.FalconData.config.ApplicationProperties;
import com.jm.pipe.FalconData.model.ChatMessage;

@Service
public class WebSocketMessageService {

    private final ApplicationProperties applicationProperties;
    private final SimpMessagingTemplate template;


    private static final Logger LOG = LoggerFactory.getLogger(WebSocketMessageService.class);

    public WebSocketMessageService(ApplicationProperties applicationProperties, SimpMessagingTemplate template) {
        this.applicationProperties = applicationProperties;
        this.template = template;
    }

    @Async
    public void sendChatMessage(ChatMessage message) {
        template.convertAndSend(applicationProperties.getTopic().getMessage(), message);
    }

    @Async
    public void sendMessageCount(Integer totalMessage) {
        LOG.info("Total Messages: {}", totalMessage);
        template.convertAndSend(applicationProperties.getTopic().getCount(), totalMessage);
    }

}
