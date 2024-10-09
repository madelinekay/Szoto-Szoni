package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APIRootDTO {
        private String name;
        private String english;

    public APIRootDTO() {}

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

    @Override
    public String toString() {
        return "APIRootDTO{" +
                "name='" + name + '\'' +
                ", english='" + english + '\'' +
                '}';
    }

}
