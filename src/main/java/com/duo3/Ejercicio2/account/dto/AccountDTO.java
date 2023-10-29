package com.duo3.Ejercicio2.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    
    private Long id;

    private String nameCustomer;

    private String numberAccount;
}