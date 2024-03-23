package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.constants.SQLQueries;
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
@NamedNativeQuery(name="UserWord.getFlaggedWords", query = SQLQueries.GET_FLAGGED_WORDS, resultClass = UserWord.class)
public class UserWord {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    int id;
    @ManyToOne
    private Word word;
    @ManyToOne
    private User user;
    private String name;
    private String english;
    private boolean flagged;
    private String notes;

    public UserWord(String name, String english, boolean flagged, String notes) {
        this.name = name;
        this.english = english;
        this.flagged = flagged;
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWord userWord = (UserWord) o;
        return id == userWord.id && flagged == userWord.flagged && Objects.equals(word, userWord.word) && Objects.equals(user, userWord.user) && Objects.equals(name, userWord.name) && Objects.equals(english, userWord.english) && Objects.equals(notes, userWord.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, user, name, english, flagged, notes);
    }
}