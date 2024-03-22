package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private int level;
    private String language;
    @OneToMany
    private Set<UserWord> userWords;

    public User(int id, String email, String password, int level, String language, Set<UserWord> userWords) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.level = level;
        this.language = language;
        this.userWords = userWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && level == user.level && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(language, user.language) && Objects.equals(userWords, user.userWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, level, language, userWords);
    }
}
