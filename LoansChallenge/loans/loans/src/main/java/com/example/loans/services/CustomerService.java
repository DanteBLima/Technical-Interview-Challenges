package com.example.loans.services;

import com.example.loans.dtos.CustomerDto;
import com.example.loans.dtos.LoanDto;
import com.example.loans.dtos.LoanResponseDto;
import com.example.loans.repositories.CustomerRepository;
import com.example.loans.users.Customer;
import com.example.loans.users.Loan;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public LoanResponseDto createCostumer(CustomerDto data) throws Exception {

        Customer newCustomer = new Customer(data);

        if (customerRepository.existsByCpf(data.cpf())){
            throw new Exception();
        }

        List<LoanDto> availableLoansForUser = availableLoans(newCustomer);
        customerRepository.save(newCustomer);
        return new LoanResponseDto(newCustomer.getName(), availableLoansForUser);
    }

    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public List<LoanDto> availableLoans(Customer customer){

        List<LoanDto> loans = new ArrayList<>();
        BigDecimal income = customer.getIncome();
        int age = customer.getAge();
        String location = customer.getLocation();

        if (income.compareTo(BigDecimal.valueOf(3000)) <= 0){
            loans.add(new LoanDto("PERSONAL", BigDecimal.valueOf(4)));
            loans.add(new LoanDto("GUARANTEED", BigDecimal.valueOf(3)));
        } else if (income.compareTo(BigDecimal.valueOf(3000)) >= 0 && income.compareTo(BigDecimal.valueOf(5000)) <= 0
         && age < 30 && "SP".equalsIgnoreCase(location)) {
            loans.add(new LoanDto("PERSONAL", BigDecimal.valueOf(4)));
            loans.add(new LoanDto("GUARANTEED", BigDecimal.valueOf(3)));
        }

        if (income.compareTo(BigDecimal.valueOf(5000)) >= 0 ){
            loans.add(new LoanDto("CONSIGNMENT", BigDecimal.valueOf(2)));
        }

        return loans;
    }
}
