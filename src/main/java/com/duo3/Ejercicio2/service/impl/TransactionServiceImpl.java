package com.duo3.Ejercicio2.service.impl;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duo3.Ejercicio2.dto.request.TransactionRequestDTO;
import com.duo3.Ejercicio2.dto.response.TransactionResponseDTO;
import com.duo3.Ejercicio2.exception.ResourceNotFoundException;
import com.duo3.Ejercicio2.exception.ValidationException;
import com.duo3.Ejercicio2.model.Account;
import com.duo3.Ejercicio2.model.Transaction;
import com.duo3.Ejercicio2.repository.AccountRepository;
import com.duo3.Ejercicio2.repository.TransactionRepository;
import com.duo3.Ejercicio2.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TransactionResponseDTO createTransaction(long id, TransactionRequestDTO transaction) {
        
        validationTransaction(transaction);
        BalanceValidation(transaction);
        
        Transaction transactionEntity = modelMapper.map(transaction, Transaction.class);

        //Create
        LocalDate date = LocalDate.now();

        transactionEntity.setCreateDate(date);
        transactionEntity.setAccount(accountRepository.findById(id));

        System.out.println(transactionEntity);
        
        transactionRepository.save(transactionEntity); //poyo
        
        return modelMapper.map(transactionEntity, TransactionResponseDTO.class);
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByAccountName(String name) {
        Account user = accountRepository.findByNameCustomer(name);

        List<Transaction> transactions = transactionRepository.findByAccount(user);

        List <TransactionResponseDTO> transactionsDTO = transactions
            .stream()
            .map(transaction -> modelMapper.map(transaction, TransactionResponseDTO.class))
            .collect(Collectors.toList());
        
        return transactionsDTO;
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByDateRange(LocalDate startDate, LocalDate endDate){
        List<Transaction> transactions = transactionRepository.findByCreateDateBetween(startDate, endDate);
        
        List <TransactionResponseDTO> transactionsDTO = transactions
            .stream()
            .map(transaction -> modelMapper.map(transaction, TransactionResponseDTO.class))
            .collect(Collectors.toList());
            
        return transactionsDTO;
    }

    //Validations Functions
    public void validationTransaction(TransactionRequestDTO transaction){
        if(transaction.getType() == null || transaction.getType().isEmpty()){
            throw new ValidationException("El tipo de transacción bancaria debe ser obligatorio");
        }

        if(transaction.getAmount() <= 0.00){
            throw new ValidationException("El monto en una transacción bancaria debe ser mayor de S/.0.0");
        }

        if(transaction.getAmount() > transaction.getBalance() && transaction.getType().equals("Retiro")){
            throw new ValidationException("En una transacción bancaria tipo retiro el monto no puede ser mayor al saldo.");
        }

    }

    public void BalanceValidation(TransactionRequestDTO transaction){
        if(transaction.getType().equals("Deposito")){
            transaction.setBalance(transaction.getBalance() + transaction.getAmount());
        }else if(transaction.getType().equals("Retiro")){ 
            transaction.setBalance(transaction.getBalance() - transaction.getAmount());
        }
    }

    
}
