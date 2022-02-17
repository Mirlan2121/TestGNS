package com.example.TermsOfReference.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String login;
    private String password;
    private String gmail;
    private String activationCode;
    private Long isActive;
    private String userInfo;
    private String fullName;
}
