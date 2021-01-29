package com.sang.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sang.model.Donneur;
import com.sang.model.Hopital;
import com.sang.service.DonneurService;
import com.sang.service.HopitalService;


@Controller
public class AuthentificationController {

	@Autowired
	DonneurService donneurService;
	

	@Autowired
	HopitalService hopitalService;

	@ResponseBody
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/registerdonneur", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		Donneur donneur = new Donneur();
		modelAndView.addObject("donneur", donneur);
		modelAndView.setViewName("registerdonneur"); // resources/template/registerdonneur.html
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/registerhopital", method = RequestMethod.GET)
	public ModelAndView registerhopital() {
		ModelAndView modelAndView = new ModelAndView();
		Hopital hopital = new Hopital();
		modelAndView.addObject("hopital", hopital);
		modelAndView.setViewName("registerhopital"); // resources/template/register.html
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping("/contact")
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contact"); // resources/template/contact.html
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/admin.html
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/registerdonneur", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid Donneur donneur, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(donneurService.isUserAlreadyPresent(donneur)){
			modelAndView.addObject("successMessage", "donneur already exists!");			
		}
		// we will save the user if, no binding errors
		else {
			donneurService.saveUser(donneur);
			modelAndView.addObject("successMessage", "donneur is registered successfully!");
		}
		modelAndView.addObject("donneur", new Donneur());
		modelAndView.setViewName("registerdonneur");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/registerhopital", method=RequestMethod.POST)
	public ModelAndView registerhopital(@Valid Hopital hopital, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(hopitalService.isUserAlreadyPresent(hopital)){
			modelAndView.addObject("successMessage", "hopital already exists!");			
		}
		// we will save the user if, no binding errors
		else {
			hopitalService.saveUser(hopital);
			modelAndView.addObject("successMessage", "hopital is registered successfully!");
		}
		modelAndView.addObject("hopital", new Hopital());
		modelAndView.setViewName("registerhopital");
		return modelAndView;
	}
	
	
}

