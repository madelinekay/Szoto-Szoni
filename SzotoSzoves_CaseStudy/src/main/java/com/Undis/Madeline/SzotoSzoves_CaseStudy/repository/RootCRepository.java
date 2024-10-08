package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

//todo: there was a mistype in JPARepo
import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoRootsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.RootC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface RootCRepository extends JpaRepository<RootC, Integer> {
    RootC findByName(String name);
    @Query(value = "select * from rootc r inner join root_word wr on r.id=wr.rootc_id where wr.wordc_id=:wordId order by wr.position", nativeQuery = true)
    List<RootC> getRootsInOrder(@Param("wordId") int wordId)
            throws NoRootsFoundException;
}
