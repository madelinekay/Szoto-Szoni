package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.WordC;

public class UserWordDTO {
    private String name;
    private String english;
    private User user;
    private WordC word;

    public void setName(String name) {
        this.name = name;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WordC getWord() {
        return word;
    }

    public void setWord(WordC word) {
        this.word = word;
    }

    public String getName() {
        return name;
    }

    public String getEnglish() {
        return english;
    }

}
