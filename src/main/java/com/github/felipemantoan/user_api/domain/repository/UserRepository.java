package com.github.felipemantoan.user_api.domain.repository;

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
public interface UserRepository extends MongoRepository<User, String> {
    
    @Query("{'_id' : ?0, 'deleted': false }")
    @Update("{ 'deleted': true }")
    public void disable(String id);

    @Query("{'deleted': { $ne : true }}")
    public Page<User> findAllNoDeleted(Pageable pageable);
}
