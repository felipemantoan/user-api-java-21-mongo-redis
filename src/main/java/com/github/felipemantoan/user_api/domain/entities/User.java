package com.github.felipemantoan.user_api.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.caelum.stella.bean.validation.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Document(collection = "user")
public record User(
    @Id UUID id, 
    @Field("name") @NotEmpty @Length(min = 10, max = 255) String name, 
    @Field("cpf") @CPF @Indexed(unique = true) String cpf, 
    @Field("email") @NotEmpty @Indexed(unique = true) @Email String email, 
    @Field("phone_number") @NotEmpty @Length(min = 10, max = 11) String phoneNumber,
    @Field("created_at") @CreatedDate LocalDateTime createdAt,
    @Field("updated_at") @LastModifiedDate LocalDateTime updatedAt,
    @Field("deleted") Boolean deleted) {

    public User {
        deleted = Boolean.FALSE;
    }
}