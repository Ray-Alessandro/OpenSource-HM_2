package com.duo3.Ejercicio2.account.service.impl;
import com.duo3.Ejercicio2.account.dto.AccountDTO;
import com.duo3.Ejercicio2.shared.exception.ResourceNotFoundException;
import com.duo3.Ejercicio2.shared.exception.ValidationException;
import com.duo3.Ejercicio2.account.model.Account;
import com.duo3.Ejercicio2.account.repository.AccountRepository;
import com.duo3.Ejercicio2.account.service.AccountService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AccountDTO createAccount(AccountDTO account) {
        
        //Validation
        validationAccount(account);

        Account accountEntity = modelMapper.map(account, Account.class);
        //accountEntity.setId((long)100);
        System.out.println(accountEntity);
        accountRepository.save(accountEntity);
        
        AccountDTO accountDTO = modelMapper.map(accountEntity, AccountDTO.class);
        return accountDTO;
    }

    @Override
    public List<AccountDTO> getAccounts() {
        //Account accountEntity
        List<Account> accounts = accountRepository.findAll();

        List<AccountDTO> accountsDTO = accounts
            .stream()
            .map(account -> modelMapper.map(account, AccountDTO.class))
            .collect(Collectors.toList());
        
        
        return accountsDTO;
    }

    //Funtions

    public void validationAccount(AccountDTO account){
        if (account.getNameCustomer() == null || account.getNameCustomer().isEmpty()) { //""
            throw new ResourceNotFoundException("El nombre del cliente debe ser obligatorio");
        }

        
        if(account.getNameCustomer().length() > 30) {
            System.out.println(account.getNameCustomer().length());
            throw new ValidationException("El nombre del cliente no debe exceder los 30 caracteres");
        }

        if (account.getNumberAccount() == null || account.getNumberAccount().isEmpty()) { 
            throw new ResourceNotFoundException("El número de cuenta debe ser obligatorio");
        }

        if(account.getNumberAccount().length() != 13) {
            throw new ValidationException("El número de cuenta debe tener una longitud de 13 caracteres");
        }

        if (accountRepository.existsByNameCustomerOrNumberAccount(account.getNameCustomer(), account.getNumberAccount())) {
            throw new ValidationException("No se puede registrar la cuenta porque ya existe uno con estos datos");
        }
    }
}