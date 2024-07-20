package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.WordRepositoryException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.WordC;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordCRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordCService {
    private WordCRepository wordCRepository;

    @Autowired
    public WordCService(WordCRepository wordCRepository) {
        this.wordCRepository = wordCRepository;
    }

    public WordC getWord() throws WordRepositoryException {
        Optional<WordC> wordOptional = wordCRepository.findRandomWord();
        if(wordOptional.isPresent()) {
            WordC word = wordOptional.get();
            System.out.println("Word: " + word);
            return word;
        } else {
            throw new
                    WordRepositoryException("No word found in the repository."); }
    }

    public List<WordC> getWords() {
        return wordCRepository.findAll();
    }

    public Optional<WordC> getWordById(int id) {
        return wordCRepository.findById(id);
    }

    public void save(WordC word) {
        wordCRepository.save(word);
    }
}
