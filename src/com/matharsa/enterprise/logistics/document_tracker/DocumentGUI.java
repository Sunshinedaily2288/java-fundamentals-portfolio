package project19.document.management;

import java.util.Scanner;

public class DocumentGUI {
    public static void main(String[] args) {
        DocumentManager manager = new DocumentManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Document System v19 ---");
        manager.addDocument("Resume", "pdf", 150.5);

        System.out.println("Quick view of stored docs:");
        manager.getAllDocs().forEach(d -> System.out.println(d.name + "." + d.extension));
    }
}

