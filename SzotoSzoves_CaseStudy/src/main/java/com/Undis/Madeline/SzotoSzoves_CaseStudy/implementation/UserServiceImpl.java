package com.Undis.Madeline.SzotoSzoves_CaseStudy.implementation;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.UserRepository;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setLevel(userDTO.getLevel());
        user.setLanguage(userDTO.getLanguage());
        userRepository.save(user);
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
