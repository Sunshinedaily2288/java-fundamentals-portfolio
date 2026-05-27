package com.matharsa.controller;

import com.matharsa.model.Ticket;
import com.matharsa.model.User;
import com.matharsa.repository.TicketRepository;
import com.matharsa.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    // Spring auto-injects both database repositories into our gateway controller
    public TicketController(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Ticket> getTicketsByStatus(@PathVariable String status) {
        return ticketRepository.findByStatus(status.toUpperCase());
    }

    @GetMapping("/priority/{priority}")
    public List<Ticket> getTicketsByPriority(@PathVariable String priority) {
        return ticketRepository.findByPriority(priority.toUpperCase());
    }

    // POST Mapping upgraded to handle relational foreign key bindings cleanly
    @PostMapping
    public Ticket createTicket(@RequestBody TicketInputDTO input) {
        // Find existing developer or create a brand new user row on the fly
        User user = userRepository.findByName(input.getAssignedTo())
                .orElseGet(() -> userRepository.save(new User(null, input.getAssignedTo())));

        Ticket ticket = new Ticket();
        ticket.setTitle(input.getTitle());
        ticket.setDescription(input.getDescription());
        ticket.setPriority(input.getPriority());
        ticket.setStatus(input.getStatus());
        ticket.setAssignedUser(user); // Map the actual structural database User entity

        return ticketRepository.save(ticket);
    }

    @PatchMapping("/{id}/status")
    public Ticket updateTicketStatus(@PathVariable Long id, @RequestParam String status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket ID not found: " + id));
        ticket.setStatus(status.toUpperCase());
        return ticketRepository.save(ticket);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return "Ticket #" + id + " was successfully purged.";
        }
        return "Ticket ID not found.";
    }

    // Static Data Transfer Object helper class to parse incoming JSON payloads cleanly
    public static class TicketInputDTO {
        private String title;
        private String description;
        private String priority;
        private String status;
        private String assignedTo;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getPriority() { return priority; }
        public void setPriority(String priority) { this.priority = priority; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getAssignedTo() { return assignedTo; }
        public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    }
}
