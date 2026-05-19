package com.matharsa.ecommerce.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PriceConversionService {

    private final Map<String, Double> exchangeRates = new HashMap<>();

    public PriceConversionService() {
        // Core baseline exchange rates relative to USD base currency
        exchangeRates.put("USD", 1.00);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("GBP", 0.79);
    }

    public double convertPrice(double basePriceInUSD, String targetCurrency) {
        String currencyKey = targetCurrency.toUpperCase().trim();

        if (!exchangeRates.containsKey(currencyKey)) {
            throw new IllegalArgumentException("Unsupported currency dimension: " + targetCurrency);
        }

        double rate = exchangeRates.get(currencyKey);
        double converted = basePriceInUSD * rate;

        // Match mathematical constraints: Round cleanly to two decimal places
        return Math.round(converted * 100.0) / 100.0;
    }
}
