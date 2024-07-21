package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.Set;

public class RootDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String english;
    private String partOfSpeech;
    private String origin;
    private String mutation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglish() {
        return english;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setEnglish(String english) {
        this.english = english;
    }


    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    @Override
    public String toString() {
        return "RootDTO{" +
                "name='" + name + '\'' +
                ", english='" + english + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", origin='" + origin + '\'' +
                ", mutation='" + mutation + '\'' +
                '}';
    }
}
