package com.github.felipemantoan.user_api.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Field.Write;

import br.com.caelum.stella.bean.validation.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Document(collection = "user")
public record User(
    @Field("_id") 
    @Id
    UUID id,
    
    @Field("name")
    @Length(min = 10, max = 255) 
    @NotEmpty
    String name,
    
    @CPF
    @Field("cpf")
    @Indexed(unique = true) 
    String cpf,
    
    @Email
    @Field("email")
    @Indexed(unique = true)
    @NotEmpty
    String email,

    @Field("phone_number")
    @NotEmpty
    @Length(min = 10, max = 11)
    String phoneNumber,
    
    @CreatedDate
    @Field("created_at")
    LocalDateTime createdAt,
    
    @Field("updated_at")
    @LastModifiedDate
    LocalDateTime updatedAt,
    
    @Field(name ="deleted", write = Write.ALWAYS)
    Boolean deleted,

    @Version
    Long version
) {

    public User {
        id = UUID.randomUUID();
        deleted = Boolean.FALSE;
    }

}