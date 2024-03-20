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
    private String wordSequence;
//    deletion of word should not delete root but should delete the instance in the join table. updates to word should merge
//    word: abc - deleting abc
//    roots:  a b c
//    root a: abc, ab -> abc should be deleted but ab should remain
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable
    private Set<Root> roots;
    private int difficulty;
    private boolean flagged;
//    private Date lastSeen;

//    do I need this? not actually used
//    changes to word should affect user words - delete should matter, and update should matter
    @ManyToMany(mappedBy = "words")
    private Set<User> users;

    public Word(int id, String name, String english, Set<Root> roots, int difficulty, Boolean flagged, Set<User> users, String wordSequence) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.roots = roots;
        this.difficulty = difficulty;
//        this.lastSeen = lastSeen;
        this.users = users;
        this.wordSequence = wordSequence;
    }

//    @Override
//    public String toString() {
//        return "Word{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", english='" + english + '\'' +
//                ", roots=" + roots +
//                ", difficulty=" + difficulty +
//                ", flagged=" + flagged +
//                ", users=" + users +
//                '}';
//    }
}
