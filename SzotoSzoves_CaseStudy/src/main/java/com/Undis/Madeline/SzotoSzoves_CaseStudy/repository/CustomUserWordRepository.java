package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.UserWord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import java.util.List;
//problem is with method name - expects query attribute to be defined in the UserWord entity
@Repository
public class CustomUserWordRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<UserWord> findByName(String query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserWord> cq = cb.createQuery(UserWord.class);
        Root<UserWord> userWord = cq.from(UserWord.class);

        Predicate namePredicate = cb.like(userWord.get("name"), "%" + query + "%");
        cq.where(namePredicate);

        return entityManager.createQuery(cq).getResultList();
    }
}