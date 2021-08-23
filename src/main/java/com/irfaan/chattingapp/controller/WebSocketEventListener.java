package com.irfaan.chattingapp.controller;

import com.irfaan.chattingapp.model.ChatMessage;
import com.irfaan.chattingapp.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


/**
 * @author Ahmad Irfaan
 * @date 8/23/2021 9:49 AM
 * web-socket
 */
@Component
public class WebSocketEventListener {

   private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

   @Autowired
   private SimpMessageSendingOperations sendingOperations;


   @EventListener
   public void handleWebSocketConnectListener(final SessionConnectedEvent event) {
      logger.info("We have a new checky little connection");
   }

   @EventListener
   public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event) {
      final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

      final String username = headerAccessor.getSessionAttributes().get("username").toString();

      final ChatMessage chatMessage = ChatMessage.builder()
              .type(MessageType.DISCONNECT)
              .sender(username)
              .build();

      sendingOperations.convertAndSend("/topic/public", chatMessage);
   }
}
