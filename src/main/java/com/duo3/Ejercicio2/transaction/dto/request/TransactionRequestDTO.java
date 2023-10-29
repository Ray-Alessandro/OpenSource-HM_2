package com.duo3.Ejercicio2.transaction.dto.request;

import com.duo3.Ejercicio2.account.model.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

    private Long id;

    private String type;

    private Double amount;

    private Double balance;

    private Account account;
}
