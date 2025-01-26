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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Document(collection = "user")
@NoArgsConstructor
public class User{

    @Id
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