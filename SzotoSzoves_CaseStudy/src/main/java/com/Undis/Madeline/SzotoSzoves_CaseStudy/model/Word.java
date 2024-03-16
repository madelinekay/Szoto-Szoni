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
public class Word {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    int id;
    private String name;
    private String english;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Root> roots;
    private int difficulty;
    private boolean flagged;
//    private Date lastSeen;
    @ManyToMany(mappedBy = "words", cascade = CascadeType.ALL)
    private Set<User> users;

    public Word(int id, String name, String english, Set<Root> roots, int difficulty, Boolean flagged, Set<User> users) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.roots = roots;
        this.difficulty = difficulty;
//        this.lastSeen = lastSeen;
        this.users = users;
    }
}
