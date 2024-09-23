package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.UserWord;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserWordService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserWordController {
    private UserWordService userWordService;
    private UserService userService;

    @Autowired
    public UserWordController(UserWordService userWordService, UserService userService) {
        this.userWordService = userWordService;
        this.userService = userService;
    }

    @GetMapping("/search")
//    public String search(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "query") String query, Model model) {
    public String search(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String query, Model model) {
        // Replace the following line with your actual search implementation
//        List<UserWord> filteredWords = userWordService.findByNameIgnoreCase(query);
        List<UserWord> filteredWords = userWordService.search(query);

        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        System.out.println("FILTERED WORDS: " + filteredWords);

        model.addAttribute("user", user);
        model.addAttribute("words", filteredWords);
        return "collections";
    }
    @GetMapping("/collections")
    public String getWords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // to-do implement query

        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        List<UserWord> words = userWordService.getUserWords(user);

        model.addAttribute("user", user);
        model.addAttribute("words", words);
        return "collections";
    }

    @GetMapping("/flagged")
    public String getFlaggedWords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        List<UserWord> flaggedWords = userWordService.getFlaggedWords(user.getId());
        model.addAttribute("flaggedWords", flaggedWords);
        model.addAttribute("user", user);
        return "/flagged";
    }

    // TODO: @DeleteMapping("/words/{id}")
    @PostMapping("/delete/{wordId}")
    public String deleteWord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int wordId) {
        System.out.println("IN DELETE");
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        userWordService.deleteWord(wordId, user);
        System.out.println(email + " " + user);
        System.out.println("id " + wordId);
        return "redirect:/collections";
    }

    @PostMapping("/flag/{id}")
    public String flagWord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id) {
        String email = userDetails.getUsername();
        int user_id = userService.findUserByEmail(email).getId();
        userWordService.flagWord(user_id, id);
        return "redirect:/collections";
    }
}
