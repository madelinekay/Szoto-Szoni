package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class APIWordDTO {
    private String name;
    private String english;
    private int difficulty;
    private List<APIRootDTO> roots;
    public APIWordDTO() {}
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
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
    public List<APIRootDTO> getRoots() {
        return roots;
    }
    public void setRoots(List<APIRootDTO> roots) {
        this.roots = roots;
    }
    @Override
    public String toString() {
        return "APIWordDTO{" +
                "name='" + name + '\'' +
                ", english='" + english + '\'' +
                ", difficulty=" + difficulty +
                ", roots=" + roots +
                '}';
    }
}
