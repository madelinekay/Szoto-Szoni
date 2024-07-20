package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.WordC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordCRepository extends JpaRepository<WordC, Integer> {
    @Query("SELECT w FROM WordC w ORDER BY RAND() LIMIT 1")
    Optional<WordC> findRandomWord();
}
