package com.example.amazone_database.Service;


import com.example.amazone_database.Model.User;
import com.example.amazone_database.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    // ------- CRUD --------

    // Get All Users
    public List<User> getUsers() {
          return userRepository.findAll();
    }


    // creat new User
    public void addUsers(User user) {
        userRepository.save(user);
    }


    // Update User by id
    public boolean updateUser(Integer id, User user) {
     User u=userRepository.getById(id);
     if (u==null) {
         return false;
     }

     u.setUsername(user.getUsername());
     u.setPassword(user.getPassword());
     u.setEmail(user.getEmail());
     u.setRole(user.getRole());
     u.setBalance(user.getBalance());

     userRepository.save(u);
     return true;
    }


    // delete user by id
    public boolean deleteUser(Integer id) {
        User u=userRepository.getById(id);
        if (u==null) {
            return false;
        }
        userRepository.delete(u);
        return true;
    }



    // get user by id
    public User getUserById(Integer id) {
       User u=userRepository.getById(id);
       if (u==null) {
        return null;
       }
       return u;
    }



}
