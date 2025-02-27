package com.github.felipemantoan.user_api.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.github.felipemantoan.user_api.infrastructure.validation.UniqueKeys;

import org.springframework.data.mongodb.core.mapping.Field.Write;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Document(collection = "user")
@UniqueKeys(keys = {"email", "cpf"})
@NoArgsConstructor
public class User implements Serializable {

    @MongoId
    private String id;
    
    @Field("name")
    @Length(min = 10, max = 255) 
    @NotEmpty
    private String name;
    
    @CPF
    @Field("cpf")
    @Indexed(unique = true) 
    private String cpf;
    
    @Email
    @Field("email")
    @Indexed(unique = true)
    @NotEmpty
    private String email;

    @Field("phone_number")
    @NotEmpty
    @Length(min = 10, max = 11)
    private String phoneNumber;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;
    
    @Field("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @Field(name ="deleted", write = Write.ALWAYS)
    private Boolean deleted;

    @Version
    private Long version;
}