package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Date signUpDate;
    private int level;
    private String language;
    @ManyToMany
    @JoinTable
    private Set<Word> words;

    public User(String email, String firstName, String lastName, Date signUpDate, Set<Word> words) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.signUpDate = signUpDate;
        this.words = words;
    }
}
