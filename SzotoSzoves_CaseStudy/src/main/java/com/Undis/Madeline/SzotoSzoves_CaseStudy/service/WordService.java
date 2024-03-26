package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import groovy.util.logging.Slf4j;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WordService {
    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word getWord() {
        Word word = wordRepository.findRandomWord();
        System.out.println("word " + word);
        return word;
    }
    public List<Word> getWords() {
        return wordRepository.findAll();
    }
    public Optional<Word> getWordById(int id) { return wordRepository.findById(id);}

    public void save(Word word) { wordRepository.save(word); }


}

