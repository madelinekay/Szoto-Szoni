package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoWordsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import groovy.util.logging.Log4j;
import groovy.util.logging.Log4j2;
import groovy.util.logging.Slf4j;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WordService {
    @PersistenceContext
    private EntityManager entityManager;
    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word getWord() throws NoWordsFoundException {
        Word word = wordRepository.findRandomWord();

        if (word == null) {
            throw new NoWordsFoundException("Random word not returned - database is empty.");
        }
        return word;

    }
    public List<Word> getWords() {
        return wordRepository.findAll();
    }
    public Optional<Word> getWordById(int id) { return wordRepository.findById(id);}

//    public void deleteWord() { return wordRepository.deleteById(id); }
    public void save(Word word) { wordRepository.save(word); }

    public List<Word> getFlaggedWords() {
        List<Word> words = entityManager.createNamedQuery("Word.getFlaggedWords").setParameter(1, true).getResultList();
        return words;
    }
}

