package com.duo3.Ejercicio2.account.service;

import java.util.List;

import com.duo3.Ejercicio2.account.dto.AccountDTO;

public interface AccountService {
    
    public abstract AccountDTO createAccount(AccountDTO account);

    public abstract List<AccountDTO> getAccounts();
    
}