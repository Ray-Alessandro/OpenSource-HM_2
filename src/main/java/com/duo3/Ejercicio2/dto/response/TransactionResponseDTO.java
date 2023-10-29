package com.duo3.Ejercicio2.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    
    private Long id;

    private String type;

    private LocalDate createDate;

    private Double amount;

    private Double balance;

}