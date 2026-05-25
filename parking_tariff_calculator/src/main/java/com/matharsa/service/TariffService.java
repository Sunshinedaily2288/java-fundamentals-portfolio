package com.matharsa.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TariffService {

    private static final BigDecimal HOURLY_RATE = new BigDecimal("2.50");
    private static final BigDecimal DAILY_MAX = new BigDecimal("25.00");

    public BigDecimal calculateFee(LocalDateTime entry, LocalDateTime exit) {
        if (exit.isBefore(entry)) {
            throw new IllegalArgumentException("Exit time cannot be before entry time.");
        }

        long totalMinutes = Duration.between(entry, exit).toMinutes();

        // 1. Check for grace period
        if (totalMinutes <= 15) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        // 2. Round up partial hours (standard garage policy)
        long totalHours = (long) Math.ceil((double) totalMinutes / 60.0);

        // 3. Compute base cost
        BigDecimal fee = HOURLY_RATE.multiply(new BigDecimal(totalHours));

        // 4. Extract total full days to apply cap limits correctly
        long totalDays = totalHours / 24;
        long remainingHours = totalHours % 24;

        BigDecimal cappedFee = DAILY_MAX.multiply(new BigDecimal(totalDays));
        BigDecimal remainingFee = HOURLY_RATE.multiply(new BigDecimal(remainingHours));

        if (remainingFee.compareTo(DAILY_MAX) > 0) {
            remainingFee = DAILY_MAX;
        }

        fee = cappedFee.add(remainingFee);
        return fee.setScale(2, RoundingMode.HALF_UP);
    }
}
