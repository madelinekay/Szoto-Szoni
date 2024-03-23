package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoWordsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.RootService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class WordController {
    private WordService wordService;
    private UserService userService;
    private RootService rootService;

    @Autowired
    public WordController(WordService wordService, UserService userService, RootService rootService) {
        this.wordService = wordService;
        this.userService = userService;
        this.rootService = rootService;
    }

    @GetMapping("/flagged")
    public String getFlaggedWords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Word> flaggedWords = wordService.getFlaggedWords();
        model.addAttribute("flaggedWords", flaggedWords);
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        return "/flagged";
    }

    @GetMapping("/collections")
    public String getWords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        List<Word> words = wordService.getWords();
//        String email = userDetails.getUsername();
//        User user = userService.findUserByEmail(email);
//        List<Word> words = user.getWords().stream().toList();
//        model.addAttribute("user", user);
//        model.addAttribute("words", words);
        return "collections";
    }

    @GetMapping("/flashcard")
    public String getWord(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        //    adding word to user words
//       todo add logic so that words dont repeat
//        System.out.println("flashcard controller");
//        try {
//            Word word = wordService.getWord();
//            System.out.println(word + "" + "word");
//
//
//            String[] rootStrings = word.getWordSequence().split(" ");
//            System.out.println("word controller " + rootStrings);
//            List<Root> roots = new ArrayList<>();
//            for (String rootString : rootStrings) {
//                Root root = rootService.getRootByName(rootString);
//                System.out.println("word controller " + root);
//                roots.add(root);
//            }
//            System.out.println("roots " + roots);
//
//            String email = userDetails.getUsername();
//            User user = userService.findUserByEmail(email);
//            user.getWords().add(word);
////            word.getUsers().add(user);
//
//            userService.save(user);
//
//            //    adding word to model and returning flashcard view
//            model.addAttribute("user", user);
//            model.addAttribute("roots", roots);
//            model.addAttribute("word", word);
//            model.addAttribute("isFlipped", false);
//        } catch (NoWordsFoundException e) {
//            System.out.println("An exception occurred: " + e.getMessage());
//        } finally {
            return "/flashcard";
//        }
    }


    //    @PostMapping("/flashcard")
//    public String getWordFromChatGPT(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
//        WordDTO wordDTO = PythonAPIClient.getWord();
//        model.addAttribute("word", wordDTO);
//        return "flashcard";  // name of the Thymeleaf template
//    }
//do  I need id in the set if I'm only removing from the user set? should I return the view to update the deleted item
    // TODO: @DeleteMapping("/words/{id}")
    @GetMapping("/delete/{id}")
    public String deleteWord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id) {
//        String email = userDetails.getUsername();
//        User user = userService.findUserByEmail(email);
//        Optional<Word> optionalWord = wordService.getWordById(id);
//        optionalWord.ifPresent(word -> {
//            user.getWords().remove(word);
////            word.getUsers().remove(user);
//            userService.save(user);
//            wordService.save(word);
//        });

        return "redirect:/collections";
    }

    @GetMapping("/flag/{id}")
    public String flagWord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id) {
//        String email = userDetails.getUsername();
//        User user = userService.findUserByEmail(email);
//        for (Word word : user.getWords()) {
//            System.out.println(word);
//            if (word.getId() == id) {
//                word.setFlagged(true);
//                System.out.println("inside controller " + word.isFlagged());
//            }
//        }
//        userService.save(user);
        return "redirect:/collections";
    }
}
