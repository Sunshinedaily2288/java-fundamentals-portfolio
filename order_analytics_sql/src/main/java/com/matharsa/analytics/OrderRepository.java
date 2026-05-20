package com.matharsa.analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderRecord, Long> {
    // Custom SQL queries can be declared here if needed later!
}
