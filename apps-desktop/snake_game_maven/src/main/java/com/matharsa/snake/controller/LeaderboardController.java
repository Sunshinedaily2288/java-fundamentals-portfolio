package com.matharsa.snake.controller;

import com.matharsa.snake.model.LeaderboardEntry;
import com.matharsa.snake.repository.LeaderboardRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {

    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardController(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    @GetMapping
    public List<LeaderboardEntry> getTopScores() {
        return leaderboardRepository.findTop10ByOrderByScoreDesc();
    }

    @PostMapping
    public LeaderboardEntry saveScore(@RequestBody LeaderboardEntry entry) {
        // Automatically inject current server timestamp before locking it into rows
        LeaderboardEntry newEntry = new LeaderboardEntry(entry.getPlayerName(), entry.getScore());
        return leaderboardRepository.save(newEntry);
    }
}
