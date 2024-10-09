package com.example.contactform.controller;

import com.example.contactform.model.Contact;
import com.example.contactform.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/submit")
    public String submitForm(Contact contact) {
        contactRepository.save(contact);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}
