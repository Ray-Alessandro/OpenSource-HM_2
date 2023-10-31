package com.duo3.Ejercicio2.transaction.controller;

import com.duo3.Ejercicio2.transaction.dto.request.TransactionRequestDTO;
import com.duo3.Ejercicio2.transaction.dto.response.TransactionResponseDTO;
import com.duo3.Ejercicio2.transaction.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bank/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //URL: http://localhost:8080/api/bank/v1/transactions/filterByNameCustomer
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByNameCustomer")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(@RequestParam("nameCustomer") String nameCustomer) {
        return new ResponseEntity<List<TransactionResponseDTO>>(transactionService.getTransactionByAccountName(nameCustomer), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/bank/v1/transactions/filterByCreateDateRange
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByCreateDateRange")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsByDateRange(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return new ResponseEntity<List<TransactionResponseDTO>>(transactionService.getTransactionByDateRange(startDate, endDate), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/bank/v1/account/1/transactions
    @PostMapping("/accounts/{id}/transactions")
    @Transactional
    public ResponseEntity<TransactionResponseDTO> createTransaction(@PathVariable("id") long id, @RequestBody TransactionRequestDTO transaction) {
        return new ResponseEntity<TransactionResponseDTO>(transactionService.createTransaction(id, transaction), HttpStatus.CREATED);
    }
}
