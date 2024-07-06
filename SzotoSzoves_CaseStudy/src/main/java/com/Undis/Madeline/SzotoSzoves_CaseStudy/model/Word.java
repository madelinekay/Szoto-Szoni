package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.constants.SQLQueries;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("words")
    private Set<Root> roots;
    private int difficulty;
    private boolean flagged;
    private String partOfSpeech;
    private String image;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("word")
    private Set<UserWord> userWords;

    public Word(int id, String name, String english, String wordSequence, Set<Root> roots, int difficulty, boolean flagged, String partOfSpeech, Set<UserWord> userWords) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.wordSequence = wordSequence;
        this.roots = roots;
        this.difficulty = difficulty;
        this.flagged = flagged;
        this.partOfSpeech = partOfSpeech;
        this.userWords = userWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id == word.id && difficulty == word.difficulty && flagged == word.flagged && Objects.equals(name, word.name) && Objects.equals(english, word.english) && Objects.equals(wordSequence, word.wordSequence) && Objects.equals(roots, word.roots) && Objects.equals(userWords, word.userWords);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, english, wordSequence, roots, difficulty, flagged, userWords);
//    }
}
