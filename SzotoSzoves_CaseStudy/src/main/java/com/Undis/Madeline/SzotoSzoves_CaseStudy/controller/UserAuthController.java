package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserAuthController {
    private UserService userService;

    @Autowired
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping("/home")
    public String returnFlashcard() {
        return "redirect:/flashcard";
    }

    @PostMapping("/signup/save")
    public String registration(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirAttrs
    ) {
        User existingUser = userService.findUserByEmail(userDTO.getEmail());
        if (existingUser != null) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "/signup";
        }
        userService.saveUser(userDTO);
        redirAttrs.addFlashAttribute("message", "Signup successful. Please login.");
        return "redirect:/login";
    }
}

