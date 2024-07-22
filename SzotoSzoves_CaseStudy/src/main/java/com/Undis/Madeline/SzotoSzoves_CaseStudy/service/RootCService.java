package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoRootsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.RootC;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.RootCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootCService {
    private final RootCRepository rootCRepository;

    @Autowired
    public RootCService(RootCRepository rootCRepository) {
        this.rootCRepository = rootCRepository;
    }

    public RootC getRootByName(String name) {
        RootC root = rootCRepository.findByName(name);
        System.out.println(root);
        return root;
    }

    public List<RootC> getRootsInOrder(int wordId)
            throws NoRootsFoundException
    {
        List<RootC> roots = rootCRepository.getRootsInOrder(wordId);
        if (roots.isEmpty()) {
            System.out.println("root service no roots");
            throw new NoRootsFoundException("No roots found in the database for the given word ID: " + wordId);
        }
        return roots;
    }
}
