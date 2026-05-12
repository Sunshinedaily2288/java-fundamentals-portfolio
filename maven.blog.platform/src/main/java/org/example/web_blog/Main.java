package org.example.web_blog;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<BlogPost> myBlog = new ArrayList<>();

        System.out.println("--- WELCOME TO MY JAVA BLOG ---");

        while (true) {
            System.out.print("\n1. New Post | 2. View All | 3. Exit: ");
            int choice = input.nextInt();
            input.nextLine(); // Clear buffer

            if (choice == 3) break;

            if (choice == 1) {
                System.out.print("Title: ");
                String title = input.nextLine();
                System.out.print("Content: ");
                String body = input.nextLine();
                myBlog.add(new BlogPost(title, body));
                System.out.println("Post Published!");
            } else if (choice == 2) {
                if (myBlog.isEmpty()) System.out.println("No posts yet!");
                for (BlogPost p : myBlog) System.out.println(p);
            }
        }
    }
}