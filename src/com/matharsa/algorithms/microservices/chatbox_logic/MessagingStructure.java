package com.matharsa.algorithms.chatbox_logic;

import java.time.LocalTime;

public class MessagingStructure {
    String sender;
    String content;
    String timestamp;

    public MessagingStructure(String sender, String content) {
        this.sender = sender;
        this.content = content;
        // Automatically adds the time the message was sent
        this.timestamp = LocalTime.now().toString().substring(0, 5);
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + sender + ": " + content;
    }
}
