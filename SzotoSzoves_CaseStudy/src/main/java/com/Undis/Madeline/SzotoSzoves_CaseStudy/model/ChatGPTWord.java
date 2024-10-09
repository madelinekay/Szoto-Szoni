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
public class  ChatGPTWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String english;

    private String image;

    private int difficulty;

    @ManyToMany
    @JoinTable(
            name = "chatGPTword_chatGPTRoot",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "root_id")
    )
    private List<ChatGPTRoot> roots = new ArrayList<>();

    public ChatGPTWord(String name, String english, String image, int difficulty) {
        this.name = name;
        this.english = english;
        this.image = image;
        this.difficulty = difficulty;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<ChatGPTRoot> getRoots() {
        return roots;
    }

    public void setRoots(List<ChatGPTRoot> roots) {
        this.roots = roots;
    }

    @Override
    public String toString() {
        return "ChatGPTWord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", english='" + english + '\'' +
                ", image='" + image + '\'' +
                ", difficulty=" + difficulty +
                ", roots=" + roots +
                '}';
    }
}