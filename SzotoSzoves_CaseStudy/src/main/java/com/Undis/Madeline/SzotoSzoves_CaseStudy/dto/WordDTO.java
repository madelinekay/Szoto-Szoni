package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.Set;

public class WordDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String english;

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
