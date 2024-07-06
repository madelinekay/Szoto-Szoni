package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTRoot;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatGPTWordRepository extends JpaRepository<ChatGPTWord, Integer> {
    Optional<ChatGPTRoot> findByName(String name);
}
