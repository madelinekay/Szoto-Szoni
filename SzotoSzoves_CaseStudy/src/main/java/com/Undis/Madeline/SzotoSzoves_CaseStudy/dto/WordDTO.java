package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class WordDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String english;
    private String wordSequence;
    private List<RootDTO> roots;


    public WordDTO(String name, String english, String wordSequence, List roots) {
        this.name = name;
        this.english = english;
        this.wordSequence = wordSequence;
        this.roots = roots;
    }
    public WordDTO() {};

    public String getWordSequence() {
        return wordSequence;
    }

    public void setWordSequence(String wordSequence) {
        this.wordSequence = wordSequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
