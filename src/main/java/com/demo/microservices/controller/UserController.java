package com.demo.microservices.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;
	
    @GetMapping("/auth/message/message")
    public String messagePageRender(Model model, HttpServletRequest request) {

        log.info("LocaleResolver : {}", messageSource.getMessage("hello", new Object[]{}, localeResolver.resolveLocale(request)));

     
    	log.info("message=====");
        model.addAttribute("price" , 1000);

        return "layout/message/message";

    }
    
    @GetMapping("/loginForm")
    public String loginForm() {
    	return "login";
    }
}
