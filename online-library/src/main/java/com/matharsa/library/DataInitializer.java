package com.matharsa.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("⏳ Populating H2 Database with library books...");
        for (int i = 1; i <= 50; i++) {
            bookRepository.save(new Book("Clean Library Book " + i, "Author " + i));
        }
        System.out.println("✅ 50 Library books generated successfully!");
    }
}
