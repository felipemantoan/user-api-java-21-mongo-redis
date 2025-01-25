package com.github.felipemantoan.user_api.infrastructure.adapters.out.database.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.felipemantoan.user_api.domain.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID>{
    
}
