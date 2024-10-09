package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.WordC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WordCRepository extends JpaRepository<WordC, Integer> {
    @Query("SELECT w FROM WordC w where w.language = :language ORDER BY RAND() LIMIT 1")
    Optional<WordC> findRandomWord(@Param("language") String language);
}
