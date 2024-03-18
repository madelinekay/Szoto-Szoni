package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WordController {
    private WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/collections")
    public String getWords(Model model) {
        List<Word> words = wordService.getWords();
        model.addAttribute("words", words);
        return "collections";
    }

    @PostMapping ("/flashcard")
    public String getWord(Model model) {
        Word word = wordService.getWord();
        model.addAttribute("word", word);
        return "flashcard";
    }
    @DeleteMapping("/deleteword/{id}")
    public void deleteWord() {

//        get id
//        return wordService.deleteWord();
    }
}
