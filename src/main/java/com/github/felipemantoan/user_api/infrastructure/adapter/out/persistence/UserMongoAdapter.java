package com.github.felipemantoan.user_api.infrastructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.github.felipemantoan.user_api.domain.entity.User;

@Repository
@RepositoryRestResource(exported = false)
public interface UserMongoAdapter extends MongoRepository<User, String> {
    
    @Query("{'_id' : ?0, 'deleted': { $ne: true }}")
    @Update("{ 'deleted': true }")
    @Override
    @SuppressWarnings("null")
    void deleteById(String id);

    @SuppressWarnings("null")
    @Query("{'deleted': { $ne : true }}")
    @Override
    Page<User> findAll(Pageable pageable);

    @SuppressWarnings("null")
    @Query("{'_id' : ?0, 'deleted': { $ne: true }}")
    @Override
    Optional<User> findById(String id);
}
