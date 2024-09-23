package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserWordDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.UserWord;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.UserWordRepository;
import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserWordService {
    @PersistenceContext
    private EntityManager entityManager;
    private UserWordRepository userWordRepository;
    private static Logger logger = LoggerFactory.getLogger(WordService.class);

    @Autowired
    public UserWordService(EntityManager entityManager, UserWordRepository userWordRepository) {
//        this.entityManager = entityManager;
        this.userWordRepository = userWordRepository;
    }
    public List<UserWord> getUserWords(User user) {
        return userWordRepository.findAllByUser(user);
    }
    public List<UserWord> getFlaggedWords(int userId) {
        List<UserWord> words = entityManager.createNamedQuery("UserWord.getFlaggedWords").setParameter(1, userId).getResultList();
        return words;
    }
    @Transactional
    public void flagWord(int userId, int wordId) {
         try {
             userWordRepository.flagWord(userId, wordId);
         } catch (Exception e) {
           logger.atError();
         }
    }
    @Transactional
    public void deleteWord(int wordId, User user) {
        System.out.println("DELETEWORD");
        try {
            userWordRepository.deleteByIdAndUser(wordId, user);
        } catch (Exception e) {
            logger.atError();
        }
    }
//doesn't work
//    @Transactional
//    public void deleteWord(int wordId, User user) {
//        Optional<UserWord> userWordOptional = userWordRepository.findByIdAndUser(wordId, user);
//        userWordOptional.ifPresent(userWordRepository::delete);
//    }
    @Transactional
    public void convertToUserWordEntity(UserWordDTO userWordDTO, User user) {
        UserWord userWord = new UserWord();
        userWord.setName(userWordDTO.getName());
        userWord.setEnglish(userWordDTO.getEnglish());
        userWord.setFlagged(false);
        userWord.setUser(userWordDTO.getUser());
        userWord.setWord(userWordDTO.getWord());
        userWordRepository.save(userWord);
        user.getUserWords().add(userWord);
    }

    public List<UserWord> search(String query) {
        return userWordRepository.findByNameContainingIgnoreCase(query);
    }

//    public List<UserWord> findByNameIgnoreCase(String query) {
//        return userWordRepository.findByNameIgnoreCase(query);
//    }

//    public List<UserWord> search(String query) {
//        return userWordRepository.findByName(query);
//    }
}
