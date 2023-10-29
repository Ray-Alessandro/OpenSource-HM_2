package com.duo3.Ejercicio2.transaction.repository;

import com.duo3.Ejercicio2.account.model.Account;
import com.duo3.Ejercicio2.transaction.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount (Account account);
    List<Transaction> findByCreateDateBetween(LocalDate startDate, LocalDate endDate);
}
