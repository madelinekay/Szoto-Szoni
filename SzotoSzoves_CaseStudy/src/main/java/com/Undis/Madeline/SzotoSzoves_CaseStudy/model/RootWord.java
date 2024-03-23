package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RootWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Word word;
    @ManyToOne
    private Root root;
    private int position;

    public RootWord(Word word, Root root, int position) {
        this.word = word;
        this.root = root;
        this.position = position;
    }
}
