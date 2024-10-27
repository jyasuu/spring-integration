package org.jyasu.postgresql.transaction.repository;

import org.jyasu.postgresql.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}