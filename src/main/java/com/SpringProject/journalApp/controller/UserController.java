package com.SpringProject.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SpringProject.journalApp.service.UserEntryService;

import java.time.LocalDateTime;
import java.util.List;

import com.SpringProject.journalApp.enity.JournalEntry;
import com.SpringProject.journalApp.enity.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<User> getAllUsers(){
        return userEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<User> createUserEntry(@RequestBody User myEntry){
        try{
            userEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{userName}")
    public ResponseEntity<User> updateJournalEntry(@PathVariable String userName, @RequestBody User userEntry){
        User userInDB = userEntryService.findByUserName(userName);
        if(userName==null || userName.isEmpty() || userEntry==null || userInDB==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userInDB.setUsername(userEntry.getUsername());
        userInDB.setPassword(userEntry.getPassword());
        userEntryService.saveEntry(userInDB);
        return new ResponseEntity<>(userEntry,HttpStatus.OK);
    }
}
