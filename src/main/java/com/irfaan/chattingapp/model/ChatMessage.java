package com.irfaan.chattingapp.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Ahmad Irfaan
 * @date 8/23/2021 9:41 AM
 * web-socket
 */

@Builder
public class ChatMessage {

   @Getter
   private MessageType type;

   @Getter
   private String content;

   @Getter
   private String sender;

   @Getter
   private String time;

}
