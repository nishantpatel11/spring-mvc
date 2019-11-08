package com.spring.demo.mvc.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	 
	    @GetMapping("/user")
	    public String userForm(Locale locale, Model model) {
	        model.addAttribute("users", "Hello");
	        return "editUsers";
	    }
	    
	    
	    @GetMapping("/editUser")
	    public String user(Locale locale, Model model) {
	        model.addAttribute("users", "Hello");
	        return "editUsers";
	    }
	    
	    
	    @RequestMapping(value = "/users/{name}", method = RequestMethod.GET)
		public ModelAndView hello(@PathVariable("name") String name) {

			ModelAndView model = new ModelAndView();
			model.setViewName("hello");
			model.addObject("msg", name);

			return model;

		}
}
