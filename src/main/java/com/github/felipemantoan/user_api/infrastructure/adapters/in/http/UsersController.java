package com.github.felipemantoan.user_api.infrastructure.adapters.in.http;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.felipemantoan.user_api.domain.entities.User;
import com.github.felipemantoan.user_api.infrastructure.adapters.out.database.repositories.UserRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/users")
@Log4j2
public class UsersController {
    
    @Autowired private UserRepository repository;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable("userId") UUID userId) {
        log.info(userId);
        User user = new User(UUID.randomUUID(), "Mantoan", "93632927030", "1912348765", "email@email.com", null, null, null);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(repository.findAll(pageable));
    }

}
