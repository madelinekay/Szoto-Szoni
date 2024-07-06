package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Root {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    int id;
    private String name;
    private String english;
    @ManyToMany(mappedBy = "roots", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("roots")
    private Set<Word> words;
    private String partOfSpeech;
    private String origin;

    public Root(int id, String name, String english, Set<Word> words, String partOfSpeech, String origin) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.words = words;
        this.partOfSpeech = partOfSpeech;
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Root{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", english='" + english + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
