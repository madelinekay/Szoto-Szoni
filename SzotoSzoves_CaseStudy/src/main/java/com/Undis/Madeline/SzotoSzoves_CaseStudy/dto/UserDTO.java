package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.Set;

public class UserDTO {
    private int id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private Date signUpDate;
    @NotEmpty
    private int level;
    @NotEmpty
    private String language;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }
}

