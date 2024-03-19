package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.WordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.PythonAPIClient;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class WordController {
    private WordService wordService;
    private UserService userService;

    @Autowired
    public WordController(WordService wordService, UserService userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @GetMapping("/collections")
    public String getWords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        List<Word> words = wordService.getWords();
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        List<Word> words = user.getWords().stream().toList();
        model.addAttribute("words", words);
        return "collections";
    }

//    @PostMapping ("/flashcard")
//    public String getWord(Model model) {
//        Word word = wordService.getWord();
//        model.addAttribute("word", word);
//        return "flashcard";
//    }
    @PostMapping ("/flashcard")
    public String getWord(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    //    adding word to user words
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        Word word = wordService.getWord();
        user.getWords().add(word);
        word.getUsers().add(user);
        userService.save(user);
    //    adding word to model and returning flashcard view
        model.addAttribute("word", word);
        return "flashcard";
    }

//    @GetMapping( "/")
//    public String returnView() {
//        return "flashcard";
//    }
//    @PostMapping("/flashcard")
//    public String getWordFromChatGPT(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
//        WordDTO wordDTO = PythonAPIClient.getWord();
//        model.addAttribute("word", wordDTO);
//        return "flashcard";  // name of the Thymeleaf template
//    }
//do  I need id in the set if I'm only removing from the user set? should I return the view to update the deleted item
    @DeleteMapping("/deleteword/{id}")
    public void deleteWord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id) {
        System.out.println("deleting");
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
    //        get word by id
        Optional<Word> word = wordService.getWordById(id);
        user.getWords().remove(word);
    }
}