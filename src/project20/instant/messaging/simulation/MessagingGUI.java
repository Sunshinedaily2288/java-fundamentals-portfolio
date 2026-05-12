package project20.instant.messaging.simulation;

import javax.swing.JOptionPane;

public class MessagingGUI {
    public static void main(String[] args) {
        MessagingManager server = new MessagingManager();
        String partner = "JavaBot";

        // 1. Initial login pop-up
        String myName = JOptionPane.showInputDialog(null, "Enter your username:", "Instant Messenger v20", JOptionPane.QUESTION_MESSAGE);
        if (myName == null || myName.trim().isEmpty()) myName = "User1";

        while (true) {
            // 2. Main Chat Window
            String msgText = JOptionPane.showInputDialog(null,
                    "--- Chat with " + partner + " ---\n" + formatHistory(server) + "\n\nYour Message (type 'exit' to quit):",
                    "Logged in as: " + myName,
                    JOptionPane.PLAIN_MESSAGE);

            // Exit logic
            if (msgText == null || msgText.equalsIgnoreCase("exit")) break;

            // 3. Process your message
            server.sendMessage(myName, msgText);

            // 4. The "Smart" Bot Logic
            String botReply;
            String lowerMsg = msgText.toLowerCase();

            if (lowerMsg.contains("hello") || lowerMsg.contains("hi")) {
                botReply = "Hello there! How can I help you today?";
            } else if (lowerMsg.contains("status")) {
                botReply = "All systems are green. Projects 19, 20, and 21 are ready for Git!";
            } else if (lowerMsg.contains("project")) {
                botReply = "You have 21 projects in your portfolio. That's a massive achievement!";
            } else if (lowerMsg.contains("time")) {
                botReply = "The current simulation time is " + java.time.LocalTime.now().toString().substring(0, 5);
            } else {
                botReply = "That's interesting! Tell me more about '" + msgText + "'.";
            }

            // 5. Bot sends its reply to the server
            server.sendMessage(partner, botReply);
        }

        JOptionPane.showMessageDialog(null, "Chat saved to memory. Goodbye!", "Messenger", JOptionPane.INFORMATION_MESSAGE);
    }

    // Helper method to display the history in the pop-up
    private static String formatHistory(MessagingManager server) {
        StringBuilder sb = new StringBuilder();
        for (MessagingStructure m : server.getHistory()) {
            sb.append(m.toString()).append("\n");
        }
        return sb.length() == 0 ? "(No messages yet)" : sb.toString();
    }
}




