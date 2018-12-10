package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserDetails {

    private String firstname;
    private String lastname;
    @Id
    private String username;

    private String role;
    @Transient
    private String password;

}
