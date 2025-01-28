package com.example.loans.repositories;


import com.example.loans.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByCpf(String cpf);

}
