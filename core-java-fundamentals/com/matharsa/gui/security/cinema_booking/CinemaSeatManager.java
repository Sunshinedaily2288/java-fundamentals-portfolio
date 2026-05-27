package com.matharsa.gui.security.cinema_booking;

public class CinemaSeatManager {
   public static void main(String[] args) {
            // 1. Create a 2D Array (3 rows, 5 seats each)
            char[][] seats = {
                    {'O', 'O', 'X', 'O', 'O'},
                    {'O', 'X', 'X', 'O', 'O'},
                    {'O', 'O', 'O', 'O', 'O'}
            };

            System.out.println("--- Cinema Seating Chart ---");

       // Book the very first seat
       seats[0][0] = 'X';

       // 2. Nested Loop: The outer loop handles ROWS
            for (int row = 0; row < seats.length; row++) {
                System.out.print("Row " + (row + 1) + ": ");

                // 3. The inner loop handles SEATS in that row
                for (int col = 0; col < seats[row].length; col++) {
                    System.out.print(seats[row][col] + " ");
                }
                // Move to the next line after finishing a row
                System.out.println();
            }
        }
    }

