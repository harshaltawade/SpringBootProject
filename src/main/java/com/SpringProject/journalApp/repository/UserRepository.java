package com.SpringProject.journalApp.repository;
import com.SpringProject.journalApp.enity.User;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUsername(String userName);
}
