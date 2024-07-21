package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.data.RootWordId;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.RootDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserWordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.WordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoRootsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoWordsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.WordRepositoryException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.*;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.RootCRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.RootWordRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordCRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.WordRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class WordController {
    private WordService wordService;
    private UserService userService;
    private RootService rootService;
    private UserWordService userWordService;
    private RootCRepository rootCRepository;
    private WordCRepository wordCRepository;
    private RootWordRepository rootWordRepository;
    private WordCService wordCService;
    private RootCService rootCService;

    @Autowired
    public WordController(WordCService wordService, UserWordService userWordService, UserService userService, RootCService rootService, RootCRepository rootCRepository, WordCRepository wordCRepository, RootWordRepository rootWordRepository) {
        this.wordCService = wordService;
        this.userService = userService;
        this.rootCService = rootService;
        this.userWordService = userWordService;
        this.wordCRepository = wordCRepository;
        this.rootCRepository = rootCRepository;
        this.rootWordRepository = rootWordRepository;
    }

    @GetMapping("/flashcard")
    public String getWord(Model model, @AuthenticationPrincipal UserDetails userDetails)
//            throws NoWordsFoundException
    {
        try {
            System.out.println("here flashcard");
            WordC wordc = wordCService.getWord();
            System.out.println("wordc" + wordc);
            if (wordc == null) {
                System.out.println("null");
                throw new NoWordsFoundException("Database is empty");
            }
//            List<RootC> rootCs = wordc.getRootWords().stream().sorted(Comparator.comparing(RootWord::getPosition)).map(RootWord::getRootC).collect(Collectors.toList());
            List<RootDTO> rootsWithMutation = wordc.getRootWords().stream().sorted(Comparator.comparing(RootWord::getPosition)).map(rootWord -> {
                RootC rootc = rootWord.getRootC();
                RootDTO rootDTO = new RootDTO();
                rootDTO.setName(rootc.getName());
                rootDTO.setEnglish(rootc.getEnglish());
                rootDTO.setPartOfSpeech(rootc.getPartOfSpeech());
                rootDTO.setOrigin(rootc.getOrigin());
                rootDTO.setMutation(rootWord.getMutation());
                return rootDTO;
            }).collect(Collectors.toList());
//            List<RootC> rootCs = rootCService.getRootsInOrder(wordc.getId());
            System.out.println(rootsWithMutation);
//            List<RootC> rootCs = rootCService.getRootsInOrder(wordc.getId());
            System.out.println(rootsWithMutation);

            String email = userDetails.getUsername();
            User user = userService.findUserByEmail(email);
            System.out.println("user: " + user);

            if (user == null) {
                System.out.println("User not found");
                return "error"; // or some error page
            }

            UserWordDTO userWordDTO = new UserWordDTO();
            userWordDTO.setName(wordc.getName());
            userWordDTO.setEnglish(wordc.getEnglish());
            userWordDTO.setUser(user);
            userWordDTO.setWord(wordc);
            userWordService.convertToUserWordEntity(userWordDTO, user);

            userService.save(user);
            System.out.println("Saved user: " + user);

            model.addAttribute("user", user);
            model.addAttribute("rootcs", rootsWithMutation);
            model.addAttribute("word", wordc);
            model.addAttribute("isFlipped", false);

            System.out.println("Model attributes set");

        }
        catch (WordRepositoryException e) {
            System.out.println("Error occurred while fetching word from the repository: " + e.getMessage());
            model.addAttribute("errorMessage", "Error occurred while fetching word. Please try again later.");
            return "error";
        } catch (NoWordsFoundException e) {
            System.out.println("An error has occurred. Database is empty");
//        } catch (NoRootsFoundException e) {
//        System.out.println("Error: No roots found for the specified word ID");
//        model.addAttribute("errorMessage", "No roots found for the current word.");
//        return "error";
        } catch (Exception e) {
            e.printStackTrace(); // To catch any other unexpected exceptions
        } finally {
            return "flashcard";
        }
    }
}

