package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

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
//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<UserWord> userWords;


    public WordC(int id, String name, String english, String wordSequence, Set<RootWord> rootWords, int difficulty, boolean flagged, String partOfSpeech) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.wordSequence = wordSequence;
        this.rootWords = rootWords;
        this.difficulty = difficulty;
        this.flagged = flagged;
        this.partOfSpeech = partOfSpeech;
//        this.userWords = userWords;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Word word = (Word) o;
//        return id == word.id && difficulty == word.difficulty && flagged == word.flagged && Objects.equals(name, word.name) && Objects.equals(english, word.english) && Objects.equals(wordSequence, word.wordSequence) && Objects.equals(roots, word.roots) && Objects.equals(userWords, word.userWords);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, english, wordSequence, roots, difficulty, flagged, userWords);
//    }
}