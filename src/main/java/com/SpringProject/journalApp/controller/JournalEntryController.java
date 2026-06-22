package com.SpringProject.journalApp.controller;

import com.SpringProject.journalApp.enity.JournalEntry;
import com.SpringProject.journalApp.service.JournalEntryService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry journalEntry = journalEntryService.findById(myId).orElse(null);
        if(journalEntry==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean isChanged = false;
        if(!journalEntry.getTitle().equalsIgnoreCase(newEntry.getTitle())){
            journalEntry.setTitle(newEntry.getTitle());
            isChanged = true;
        }
        if(!journalEntry.getContent().equalsIgnoreCase(newEntry.getContent())){
            journalEntry.setContent(newEntry.getContent());
            isChanged = true;
        }
        if(isChanged){
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry);
        }
        return new ResponseEntity<>(journalEntry,HttpStatus.OK);
    }
}
