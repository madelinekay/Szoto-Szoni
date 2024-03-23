package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
//    private Date signUpDate;
    private int level;
    private String language;
    @OneToMany(mappedBy = "user")
    private Set<UserWord> userWords;

    public User(int id, String email, String password, int level, String language, Set<UserWord> userWords) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.level = level;
        this.language = language;
        this.userWords = userWords;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", level=" + level +
//                ", language='" + language + '\'' +
//                ", words=" + words +
//                '}';
//    }
}
