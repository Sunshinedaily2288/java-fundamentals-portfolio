package cinema_booking;
import javax.swing.*;
import java.awt.*;

    public class CinemaGUI {

    public static void main(String[] args) {
            JFrame frame = new JFrame("Cinema Booking");
            // Create a grid layout (3 rows, 5 columns)
            frame.setLayout(new GridLayout(3, 5));

            for (int i = 0; i < 15; i++) {
                JButton seat = new JButton("Seat " + (i + 1));
                seat.setBackground(Color.GREEN);

                // Logic to change color when clicked
                seat.addActionListener(e -> seat.setBackground(Color.RED));

                frame.add(seat);
            }

            frame.setSize(500, 300);
            frame.setVisible(true);
        }
    }


