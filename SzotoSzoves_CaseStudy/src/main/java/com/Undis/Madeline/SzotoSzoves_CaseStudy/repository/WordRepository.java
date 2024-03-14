package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

//user will need to be able to update or word
public interface WordRepository extends JpaRepository<Word, Integer>{
    Word findById(int id);
}
