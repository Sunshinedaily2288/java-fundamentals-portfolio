package com.matharsa.config;

import com.matharsa.model.Ticket;
import com.matharsa.model.User;
import com.matharsa.repository.TicketRepository;
import com.matharsa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(TicketRepository ticketRepository, UserRepository userRepository) {
        return args -> {
            System.out.println("🌱 Seeding relational user data tables...");

            // 1. Create and persist separate user entity rows into the users table
            User dev1 = userRepository.save(new User(null, "Mathar Sa"));
            User dev2 = userRepository.save(new User(null, "Tom"));
            User dev3 = userRepository.save(new User(null, "Sarah"));
            User dev4 = userRepository.save(new User(null, "Md"));

            System.out.println("🌱 Seeding relational tickets linked by User Foreign Keys...");

            // 2. Link the instantiated user records to our tracking tickets
            ticketRepository.save(new Ticket(null, "WebSocket Pipeline Reset Loop", "Frontend loop forces client back to login context route on connection drop.", "CRITICAL", "IN_PROGRESS", dev1));
            ticketRepository.save(new Ticket(null, "API", "no action on payload downstream endpoint responses.", "CRITICAL", "IN_PROGRESS", dev2));
            ticketRepository.save(new Ticket(null, "Optimize Database Table Joins", "Add indexes to student_id and course_id constraints for performance.", "LOW", "DONE", dev3));
            ticketRepository.save(new Ticket(null, "Auth", "no show error handler layout on structural boundaries.", "CRITICAL", "TODO", dev4));

            System.out.println("✅ Relational Database seeded! Total Tickets: " + ticketRepository.count() + " | Total Users: " + userRepository.count());
        };
    }
}
