package com.example.loans.users;


import com.example.loans.dtos.CustomerDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int age;
    private String cpf;
    private String name;
    private String location;
    private BigDecimal income;

    public Customer(CustomerDto data) {

        this.age = data.age();
        this.cpf = data.cpf();
        this.name = data.name();
        this.location = data.location();
        this.income = data.income();
    }

    public Customer(){

    }
}
