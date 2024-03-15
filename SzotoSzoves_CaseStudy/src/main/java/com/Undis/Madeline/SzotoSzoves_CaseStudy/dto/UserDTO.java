package com.Undis.Madeline.SzotoSzoves_CaseStudy.dto;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

public class UserDTO {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotNull
    private Integer level;
    @NotEmpty
    private String language;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) { this.level = level; }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

}

