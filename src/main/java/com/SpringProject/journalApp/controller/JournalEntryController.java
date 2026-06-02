package com.SpringProject.journalApp.controller;

import com.SpringProject.journalApp.enity.JournalEntry;
import com.SpringProject.journalApp.service.JournalEntryService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteJournalEntry(@PathVariable ObjectId myId){
        return journalEntryService.deleteById(myId);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry JournalEntry){
        return null;
    }
}
