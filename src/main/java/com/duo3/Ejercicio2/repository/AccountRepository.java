package com.duo3.Ejercicio2.repository;

import com.duo3.Ejercicio2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAll();

    Account findById(long id);

    Account findByNameCustomer(String name);

    boolean existsByNameCustomerOrNumberAccount(String name, String numberAccount);
}
