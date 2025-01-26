package com.github.felipemantoan.user_api.domain.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.felipemantoan.user_api.domain.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    
}
