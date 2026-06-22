package com.SpringProject.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.stereotype.Component;

import com.SpringProject.journalApp.enity.JournalEntry;
import com.SpringProject.journalApp.enity.User;
import com.SpringProject.journalApp.repository.UserRepository;
import java.util.Optional;


@Component
public class UserEntryService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User userEntry){
        userRepository.save(userEntry);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }
}
