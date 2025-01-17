package com.challenge.service;


import com.challenge.domain.user.User;
import com.challenge.domain.user.UserType;
import com.challenge.dtos.UserDto;
import com.challenge.repositories.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction (User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo 'lojista' não pode executar transação");
        }

        if (sender.getBalance().compareTo(amount) < 0 ){
            throw new Exception("Saldo insuficiente");
        }

    }

    public User findUserById(Long id) throws Exception{
        return this.userRepository.findUserById(id).orElseThrow(()->new Exception("Usuario nao encontrado"));
    }

    public void saveUser(User user) throws  Exception{


        this.userRepository.save(user);


    }

    public User createUser(UserDto data) throws Exception {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){

        return this.userRepository.findAll();

    }
}
