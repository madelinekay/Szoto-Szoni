package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

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
        try {
            userWordRepository.deleteByIdAndUser(wordId, user);
        } catch (Exception e) {
            logger.atError();
        }
    }
}
