package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.WordC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordCRepository extends JpaRepository<WordC, Integer> {
    @Query(value = "SELECT * FROM wordc ORDER BY RAND() LIMIT 1", nativeQuery = true)
    WordC findRandomWord();
}
