package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;

import java.util.Optional;

public interface UserService {
    void saveUser(UserDTO userDTO);
    User findUserByEmail(String email);
    void save(User user);
}
