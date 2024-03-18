package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.crypto.spec.PSource;
import java.util.List;

//user will need to be able to update or word
public interface WordRepository extends JpaRepository<Word, Integer>{
    @Query(value = "SELECT * FROM word ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Word findRandomWord();
}
