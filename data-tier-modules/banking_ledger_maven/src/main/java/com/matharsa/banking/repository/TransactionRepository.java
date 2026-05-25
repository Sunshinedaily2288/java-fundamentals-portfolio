package com.matharsa.banking.repository;

import com.matharsa.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 💡 Custom SQL hook to fetch the absolute latest row entry to read the current active balance
    @Query("SELECT t.runningBalance FROM Transaction t ORDER BY t.id DESC LIMIT 1")
    Double getCurrentBalance();
}
