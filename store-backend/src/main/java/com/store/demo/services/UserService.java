package com.store.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.demo.exception.RecordNotFoundException;
import com.store.demo.models.UserEntity;
import com.store.demo.repositories.UserRepository;
 
@Service
public class UserService {
     
    @Autowired
    UserRepository repository;
     
    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> userList = repository.findAll();
         
        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }
     
    public UserEntity getUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(id);
         
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
     
    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(id);
         
        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
}
