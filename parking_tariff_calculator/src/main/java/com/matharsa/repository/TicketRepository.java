package com.matharsa.repository;

import com.matharsa.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Finds active vehicles still inside the garage
    List<Ticket> findByExitTimeIsNull();
}
