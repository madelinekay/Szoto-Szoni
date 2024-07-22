package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WordC {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    int id;
    @OneToMany(mappedBy = "wordC", fetch = FetchType.EAGER)
    private Set<RootWord> rootWords;
    private String name;
    private String english;
    private String wordSequence;
    private int difficulty;
    private boolean flagged;
    private String partOfSpeech;
    private String image;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<UserWord> userWords;


    public WordC(int id, String name, String english, String wordSequence, Set<RootWord> rootWords, int difficulty, boolean flagged, String partOfSpeech) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.wordSequence = wordSequence;
        this.rootWords = rootWords;
        this.difficulty = difficulty;
        this.flagged = flagged;
        this.partOfSpeech = partOfSpeech;
        this.userWords = userWords;
    }

    @Override
    public String toString() {
        return "WordC{" +
                "id=" + id +
                ", rootWordsIds=" + rootWords.stream().map(RootWord::getId).collect(Collectors.toList()) +
                ", name='" + name + '\'' +
                ", english='" + english + '\'' +
                ", wordSequence='" + wordSequence + '\'' +
                ", difficulty=" + difficulty +
                ", flagged=" + flagged +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}