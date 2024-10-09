package com.Undis.Madeline.SzotoSzoves_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatGPTRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String english;

    @ManyToMany(mappedBy = "roots")
    private List<ChatGPTWord> words = new ArrayList<>();

    public ChatGPTRoot(String name, String english) {
        this.name = name;
        this.english = english;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ChatGPTWord> getWords() {
        return words;
    }

    public void setWords(List<ChatGPTWord> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "ChatGPTRoot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", english='" + english + '\'' +
                ", words=" + words +
                '}';
    }
}