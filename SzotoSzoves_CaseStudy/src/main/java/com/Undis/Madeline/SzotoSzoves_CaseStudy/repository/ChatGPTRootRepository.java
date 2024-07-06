package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.ChatGPTRoot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatGPTRootRepository extends JpaRepository<ChatGPTRoot, Integer> {
    Optional<ChatGPTRoot> findByName(String name);
}
