package com.matharsa.enterprise.logistics.customer_sales_crm;

import java.time.LocalDate;
import java.util.*;

public class crmManager {
    private final Map<Client, List<Interaction>> clientHistory = new HashMap<>();

    public void addInteraction(Client client, String note, LocalDate followUpDate) {
        clientHistory.computeIfAbsent(client, k -> new ArrayList<>())
                .add(new Interaction(note, followUpDate));
    }

    public Map<Client, List<Interaction>> getClientHistory() {
        return clientHistory;
    }
}

