package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserWordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.WordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoWordsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.*;
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
    private UserWordService userWordService;

    @Autowired
    public WordController(WordService wordService, UserWordService userWordService, UserService userService, RootService rootService) {
        this.wordService = wordService;
        this.userService = userService;
        this.rootService = rootService;
        this.userWordService = userWordService;
    }

    @GetMapping("/flashcard")
    public String getWord(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//       todo add logic so that words dont repeat
        try {
            Word word = wordService.getWord();
            if (word == null) {
                throw new NoWordsFoundException("Database is empty");
            }
            List<Root> roots = rootService.getRootsInOrder(word.getId());

            String email = userDetails.getUsername();
            User user = userService.findUserByEmail(email);

            UserWordDTO userWordDTO = new UserWordDTO();
            userWordDTO.setName(word.getName());
            userWordDTO.setEnglish(word.getEnglish());
            userWordDTO.setUser(user);
            userWordDTO.setWord(word);
            userWordService.convertToUserWordEntity(userWordDTO, user);
            userService.save(user);

            model.addAttribute("user", user);
            model.addAttribute("roots", roots);
            model.addAttribute("word", word);
            model.addAttribute("isFlipped", false);
        } catch (NoWordsFoundException e) {
            System.out.println("An error has occurred. Database is empty");
        } finally {

            return "/flashcard";
        }
    }
}
