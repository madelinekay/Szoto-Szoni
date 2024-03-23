package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.RootRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RootService {
    private RootRepository rootRepository;

    @Autowired
    public RootService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }
    public Root getRootByName(String name) {
        Root root = rootRepository.findByName(name);
        System.out.println(root);
        return root;
    }

}
