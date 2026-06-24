package com.SpringProject.journalApp.service;

import com.SpringProject.journalApp.enity.JournalEntry;
import com.SpringProject.journalApp.enity.User;
import com.SpringProject.journalApp.repository.JournalEntryRepository;
import com.SpringProject.journalApp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;    

    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userRepository.findByUsername(username);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userRepository.save(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public List<JournalEntry> getByUsername(String username){
        User user = userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        return journalEntries;
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id,String username){
        User user = userRepository.findByUsername(username);
        journalEntryRepository.deleteById(id);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userRepository.save(user);
        return true;
    }
}
