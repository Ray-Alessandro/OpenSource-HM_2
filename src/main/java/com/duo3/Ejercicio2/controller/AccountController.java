package com.duo3.Ejercicio2.controller;

import com.duo3.Ejercicio2.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duo3.Ejercicio2.dto.AccountDTO;

import java.util.List;

@RestController
@RequestMapping("/api/bank/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return new ResponseEntity<List<AccountDTO>>(accountService.getAccounts(), HttpStatus.OK);
    }

    @PostMapping("/accounts")
    @Transactional
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<AccountDTO>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }
}