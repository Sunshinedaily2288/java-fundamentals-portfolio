package com.matharsa.controller;

import com.matharsa.model.Ticket;
import com.matharsa.repository.TicketRepository;
import com.matharsa.service.TariffService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class TariffController {

    private final TicketRepository repository;
    private final TariffService tariffService;

    public TariffController(TicketRepository repository, TariffService tariffService) {
        this.repository = repository;
        this.tariffService = tariffService;
    }

    // 1. Enter Garage: http://localhost:8089/api/parking/enter
    @PostMapping("/enter")
    public Ticket enterGarage(@RequestParam String licensePlate) {
        Ticket ticket = new Ticket();
        ticket.setLicensePlate(licensePlate.toUpperCase());
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setCalculatedFee(BigDecimal.ZERO);
        return repository.save(ticket);
    }

    // 2. Exit Garage & Calculate Fee: http://localhost:8089/api/parking/exit/{id}
    @PostMapping("/exit/{id}")
    public Ticket exitGarage(@PathVariable Long id) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket ID not found: " + id));

        ticket.setExitTime(LocalDateTime.now());
        BigDecimal finalFee = tariffService.calculateFee(ticket.getEntryTime(), ticket.getExitTime());
        ticket.setCalculatedFee(finalFee);

        return repository.save(ticket);
    }

    // 3. List Active Cars: http://localhost:8089/api/parking/active
    @GetMapping("/active")
    public List<Ticket> getActiveVehicles() {
        return repository.findByExitTimeIsNull();
    }

    // 4. Historical Logs: http://localhost:8089/api/parking/history
    @GetMapping("/history")
    public List<Ticket> getHistory() {
        return repository.findAll();
    }
}
