package com.duo3.Ejercicio2.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 10, nullable = false)
    private String type;


    @Column(name="create_date", nullable = false)
    private LocalDate createDate;

    @Column(name="amount", nullable = false)
    private Double amount;

    @Column(name="balance", nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ACCOUNT_ID"))
    private Account account;
    
    
}
