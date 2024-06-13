package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RootRepository extends JpaRepository<Root, Integer> {
    Root findByName(String name);
    @Query(value = "select *, wr.mutation from root r inner join word_roots wr on r.id=wr.roots_id where wr.words_id=:wordId order by wr.position", nativeQuery = true)
    List<Root> getRootsInOrder(@Param("wordId") int wordId);

}
