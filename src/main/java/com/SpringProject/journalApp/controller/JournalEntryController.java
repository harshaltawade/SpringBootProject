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
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @GetMapping("/{username}")
    public List<JournalEntry> getJournalEntriesByUsername(@PathVariable String username) {
        return journalEntryService.getByUsername(username);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId, @PathVariable String username) {
        journalEntryService.deleteById(myId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry, @PathVariable String username) {
        JournalEntry journalEntry = journalEntryService.findById(myId).orElse(null);
        if (journalEntry == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean isChanged = false;
        if (!journalEntry.getTitle().equalsIgnoreCase(newEntry.getTitle())) {
            journalEntry.setTitle(newEntry.getTitle());
            isChanged = true;
        }
        if (!journalEntry.getContent().equalsIgnoreCase(newEntry.getContent())) {
            journalEntry.setContent(newEntry.getContent());
            isChanged = true;
        }
        if (isChanged) {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry);
        }
        return new ResponseEntity<>(journalEntry, HttpStatus.OK);
    }
}
