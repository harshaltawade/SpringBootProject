package com.SpringProject.journalApp.service;

import com.SpringProject.journalApp.enity.JournalEntry;
import com.SpringProject.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
        return true;
    }
}
