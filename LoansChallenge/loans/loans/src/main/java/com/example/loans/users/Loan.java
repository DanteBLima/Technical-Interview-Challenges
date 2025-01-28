package com.example.loans.users;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private double interestRate;

    @Enumerated(EnumType.STRING)
    private LoanType type;
}
