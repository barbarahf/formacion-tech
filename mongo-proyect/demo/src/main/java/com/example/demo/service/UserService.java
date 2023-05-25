package com.example.demo.service;

import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserModel> getAllUsersOrderByAge(String order) {

        return order.equalsIgnoreCase("ASC") ? userRepository.findAll(Sort.by(Sort.Direction.ASC, "age"))
                : userRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));
    }

    public List<UserModel> getAllUsersOrderByAge() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
    }

    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(String id) {

        return userRepository.findById(id);
    }

    public boolean deleteUser(String id) {
        userRepository.deleteById(id);
        return this.getUserById(id).isEmpty();
    }

    public void updateUser(UserModel user, String id) {

        userRepository.save(user);
    }
}
