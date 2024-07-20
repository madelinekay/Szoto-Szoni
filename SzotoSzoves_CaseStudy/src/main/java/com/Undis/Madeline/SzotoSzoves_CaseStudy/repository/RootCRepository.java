package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoRootsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.RootC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface RootCRepository extends JpaRepository<Root, Integer> {
    RootC findByName(String name);
    @Query(value = "select * from rootc r inner join r" +
            "oot_word wr on r.id=wr.rootc_id where wr.wordc_id=:wordId order by wr.position", nativeQuery = true)
    List<RootC> getRootsInOrder(@Param("wordId") int wordId)
//            throws NoRootsFoundException
            ;
}
