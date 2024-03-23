package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.constants.SQLQueries;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedNativeQuery(name="Word.getFlaggedWords", query = SQLQueries.GET_FLAGGED_WORDS, resultClass = UserWord.class)
public class UserWord {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    int id;
    @ManyToOne
    @JoinColumn(name="word_id", nullable=false)
    private Word word;
    @ManyToOne
    @JoinColumn(name="word_id", nullable=false)
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
}