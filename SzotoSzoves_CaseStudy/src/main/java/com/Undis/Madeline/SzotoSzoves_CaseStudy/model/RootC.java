package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

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
public class RootC {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    int id;
    @OneToMany(mappedBy = "rootC", fetch = FetchType.EAGER)
    private Set<RootWord> rootWords;
    private String name;
    private String english;
    private String partOfSpeech;
    private String origin;

    public RootC(int id, String name, String english, Set<RootWord> rootWords, String partOfSpeech, String origin) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.rootWords = rootWords;
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
