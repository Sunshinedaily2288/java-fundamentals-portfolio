package com.matharsa.library;

import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        System.out.println("⏳ Populating H2 Database with fake books...");
        for (int i = 0; i < 50; i++) {
            Book book = new Book(
                    faker.book().title(),
                    faker.book().author(),
                    faker.book().genre()
            );
            bookRepository.save(book);
        }
        System.out.println("✅ 50 Fake books generated successfully!");
    }
}
