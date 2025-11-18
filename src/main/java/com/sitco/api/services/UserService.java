package com.sitco.api.services;

import com.sitco.api.entities.User;
import com.sitco.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }
}
