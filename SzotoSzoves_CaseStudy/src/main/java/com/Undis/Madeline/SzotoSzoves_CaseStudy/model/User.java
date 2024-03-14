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
    private String password;
    private Date signUpDate;
    private int level;
    private String language;
    @ManyToMany
    @JoinTable
    private Set<Word> words;

    public User(int id, String email, String password, Date signUpDate, int level, String language, Set<Word> words) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;
        this.level = level;
        this.language = language;
        this.words = words;
    }
}
