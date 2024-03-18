package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;

import java.util.Optional;

//interface for processing web requests(login + signup) and passing to data access layer
public interface UserService {
    void saveUser(UserDTO userDTO);
    User findUserByEmail(String email);
    void save(User user);
}
