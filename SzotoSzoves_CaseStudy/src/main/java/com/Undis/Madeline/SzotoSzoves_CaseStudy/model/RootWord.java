package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.data.RootWordId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RootWord {
    @EmbeddedId
    private RootWordId id;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("rootCId")
    private RootC rootC;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("wordCId")
    private WordC wordC;
    private int position;
    private String mutation;
    public RootWord(RootWordId id, RootC rootC, WordC word, int position, String mutation) {
        this.id = id;
        this.rootC = rootC;
        this.wordC = word;
        this.position = position;
        this.mutation = mutation;
    }

}

