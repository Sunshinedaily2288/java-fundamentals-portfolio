package com.matharsa.algorithms.microservices.chatbox_logic;

import java.util.ArrayList;
import java.util.List;

public class MessagingManager { // Added "ing" to match your filename
    private List<MessagingStructure> chatHistory = new ArrayList<>();

    public void sendMessage(String user, String text) {
        // Removed the extra "()Structure" typo here
        chatHistory.add(new MessagingStructure(user, text));
    }

    public List<MessagingStructure> getHistory() {
        return chatHistory;
    }
}

