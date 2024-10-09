package com.Undis.Madeline.SzotoSzoves_CaseStudy.data;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RootWordId implements Serializable {
    private int rootCId;
    private int wordCId;
    public RootWordId() {}

    public RootWordId(int rootId, int wordId) {
        this.rootCId = rootId;
        this.wordCId = wordId;
    }

    public int getRootCId() {
        return rootCId;
    }

    public void setRootCId(int rootCId) {
        this.rootCId = rootCId;
    }

    public int getWordCId() {
        return wordCId;
    }

    public void setWordCId(int wordCId) {
        this.wordCId = wordCId;
    }
}