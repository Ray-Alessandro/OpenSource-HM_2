package com.duo3.Ejercicio2.service;

import java.time.LocalDate;
import java.util.List;

import com.duo3.Ejercicio2.dto.request.TransactionRequestDTO;
import com.duo3.Ejercicio2.dto.response.TransactionResponseDTO;

public interface TransactionService {
    public abstract TransactionResponseDTO createTransaction(long id, TransactionRequestDTO transaction);

    public abstract List<TransactionResponseDTO> getTransactionByAccountName(String name);

    public abstract List<TransactionResponseDTO> getTransactionByDateRange(LocalDate startDate, LocalDate endDate);

}
