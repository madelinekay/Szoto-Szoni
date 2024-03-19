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
public class Root {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    int id;
    private String name;
    private String english;
    @ManyToMany(mappedBy = "roots", cascade = CascadeType.ALL)
    private Set<Word> words;
//    private int difficulty;
//    private Date lastSeen;
    private String partOfSpeech;

    public Root(int id, String name, String english, Set<Word> words, String partOfSpeech) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.words = words;
//        this.difficulty = difficulty;
//        this.lastSeen = lastSeen;
        this.partOfSpeech = partOfSpeech;
    }

    @Override
    public String toString() {
        return "Root{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", english='" + english + '\'' +
                ", words=" + words +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                '}';
    }
}
