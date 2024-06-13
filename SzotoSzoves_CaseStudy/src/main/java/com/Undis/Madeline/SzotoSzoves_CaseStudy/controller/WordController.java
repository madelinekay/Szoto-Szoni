package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.data.RootWordId;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserWordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.WordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoWordsFoundException;
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

@Controller
public class WordController {
    private WordService wordService;
    private UserService userService;
    private RootService rootService;
    private UserWordService userWordService;
    private RootCRepository rootCRepository;
    private WordCRepository wordCRepository;
    private RootWordRepository rootWordRepository;

    @Autowired
    public WordController(WordService wordService, UserWordService userWordService, UserService userService, RootService rootService, RootCRepository rootCRepository, WordCRepository wordCRepository, RootWordRepository rootWordRepository) {
        this.wordService = wordService;
        this.userService = userService;
        this.rootService = rootService;
        this.userWordService = userWordService;
        this.wordCRepository = wordCRepository;
        this.rootCRepository = rootCRepository;
        this.rootWordRepository = rootWordRepository;
    }

    @GetMapping("/flashcard")
    public String getWord(Model model, @AuthenticationPrincipal UserDetails userDetails) throws NoWordsFoundException {
//       todo add logic so that words dont repeat
        try {
////            this code can eventually be used to load word from api and update database
////            RootC rootC = new RootC();
////            rootC.setOrigin("hungarian");
////            rootC.setEnglish("seed");
////            rootC.setName("mag");
////            rootC.setPartOfSpeech("noun");
////            WordC wordC = new WordC();
////            wordC.setName("maganyos");
////            wordC.setName("lonely");
////            wordC.setDifficulty(2);
////
////            RootWordId rootWordId = new RootWordId(rootC.getId(), wordC.getId());
////            RootWord rootWord = new RootWord();
////            rootWord.setId(rootWordId);
////            rootWord.setRootC(rootC);
////            rootWord.setWordC(wordC);
////            wordCRepository.save(wordC);
////            rootCRepository.save(rootC);
////            rootWordRepository.save(rootWord);
////            RootWordId id = rootWord.getId();
////            System.out.println("Embedded Id: " + rootWord.getId());
//            Wordc wordc = wordcService.getWordc();
//
//            if (wordc == null) {
//                throw new NoWordsFoundException("Database is empty");
//            }
//
//            List<RootC> rootcs = rootcService.getRootsInOrder(wordc.getId());
//
//            String email = userDetails.getUsername();
//            User user = userService.findUserByEmail(email);
//
//            UserWordDTO userWordDTO = new UserWordDTO();
//            userWordDTO.setName(wordc.getName());
//            userWordDTO.setEnglish(wordc.getEnglish());
//            userWordDTO.setUser(user);
//            userWordDTO.setWordc(wordc);
//
////Revised method to handle the new table structure
//            userWordService.convertToUserWordEntityWithWordc(userWordDTO, user);
//            userService.save(user);
//
//            model.addAttribute("user", user);
//            model.addAttribute("rootcs", rootcs);
//            model.addAttribute("wordc", wordc);
//            model.addAttribute("isFlipped", false);
//
//        } catch (NoWordsFoundException e) {
//            System.out.println("An error has occurred. Database is empty");
//        } finally {
//            return "/flashcard";
//        }

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
