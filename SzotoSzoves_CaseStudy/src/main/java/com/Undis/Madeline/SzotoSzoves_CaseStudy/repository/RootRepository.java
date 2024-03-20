package com.Undis.Madeline.SzotoSzoves_CaseStudy.repository;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootRepository extends JpaRepository<Root, Integer> {
    Root findByName(String name);
}
