package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word getWord() {
        Word word = wordRepository.findRandomWord();
        return word;
    }
    public List<Word> getWords() {
        return wordRepository.findAll();
    }
    public Optional<Word> getWordById(int id) { return wordRepository.findById(id);}

//    public void deleteWord() { return wordRepository.deleteById(id); }

}
