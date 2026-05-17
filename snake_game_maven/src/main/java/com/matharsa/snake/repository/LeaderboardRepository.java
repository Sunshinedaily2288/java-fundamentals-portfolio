package com.matharsa.snake.repository;

import com.matharsa.snake.model.LeaderboardEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntry, Long> {
    // 💡 Magic method sorting the scoreboard top down by highest numbers first, limiting to top 10 rows
    List<LeaderboardEntry> findTop10ByOrderByScoreDesc();
}
