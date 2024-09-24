package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserWordRepository extends JpaRepository<UserWord, Integer> {
    List<UserWord> findAllByUser(User user);

    @Modifying
    @Query("UPDATE UserWord w SET w.flagged = true WHERE w.id = :wordId AND w.user.id = :userId")
    void flagWord(@Param("userId") int userId, @Param("wordId") int wordId);

    @Modifying
    @Query("DELETE FROM UserWord u WHERE u.id = :wordId AND u.user = :user")
    void deleteByIdAndUser(@Param("wordId") int wordId, @Param("user") User user);

    List<UserWord> findByNameContainingIgnoreCase(String name);
}
