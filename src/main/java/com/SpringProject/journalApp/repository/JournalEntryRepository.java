package com.SpringProject.journalApp.repository;

import com.SpringProject.journalApp.enity.JournalEntry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,ObjectId> {
}


//MongoRepository<JournalEntity,ObjectId> : <Entity(POJO), Primary_Key_type>