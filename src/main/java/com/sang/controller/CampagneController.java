package com.sang.controller;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
 
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;

import com.sang.model.Campagne;
import com.sang.repository.AdminRepository;
import com.sang.repository.ArticleRepository;
import com.sang.repository.CampagneRepository;
import com.sang.repository.DonneurRepository;
import com.sang.repository.HopitalRepository;
import com.sang.service.AdminService;
import com.sang.service.CampagneService;
import com.sang.service.DonneurService;
import com.sang.service.HopitalService;



//mark class as Controller  
@Controller  
public class CampagneController   
{  
	@Autowired
	CampagneRepository CampagneRepository;
	
	@Autowired
	ArticleRepository ArticleRepository;
	
	@Autowired
	AdminRepository AdminRepository;
	
	@Autowired
	AdminService AdminService;
	
	@Autowired
	DonneurService donneurService;
	
	@Autowired
	HopitalService hopitalService;

	@Autowired
	AdminService adminService;
	
	
	@Autowired
	DonneurRepository DonneurRepository;
	
	@Autowired
	HopitalRepository HopitalRepository;
	
	@Autowired
	CampagneService campagneser;

	@GetMapping("/all_campagne")
    public String showAllCampagne(Campagne campagne,Model model, Principal principal) {
		
		int currentPage = 1;
		Page<Campagne> page = campagneser.listAll(currentPage);
		long totalItems  = page.getTotalElements();
		int totalPages  = page.getTotalPages();
		List<Campagne> campagnes = page.getContent();
		
		if (principal==null) 
		{ 		
			model.addAttribute("pagenum", currentPage);
			model.addAttribute("campagnes", campagnes);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", totalPages);
	        return "all_campagne";
			
		}
		else if(AdminRepository.selectImage(principal.getName())!= null  ){
			String image = AdminRepository.selectImage(principal.getName());
			System.out.println("image admin:" + image);
			model.addAttribute("image", image);
		}
		
		else if(DonneurRepository.selectImage(principal.getName())!= null){
			String image = DonneurRepository.selectImage(principal.getName());
			System.out.println("donneur image:" + image);
			model.addAttribute("image", image);
		}
		
		else if(HopitalRepository.selectImage(principal.getName())!= null){
			String image = HopitalRepository.selectImage(principal.getName());
			System.out.println("hopital image:" + image);
			model.addAttribute("image", image);
		}
		
		model.addAttribute("pagenum", currentPage);
		model.addAttribute("campagnes", campagnes);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
        return "all_campagne";
    }
	
	@GetMapping("/all_campagne/{i}")
    public String showAllCampagnee(Campagne campagne,Model model, Principal principal, @PathVariable("i") int i) {
		
		int currentPage = i;
		Page<Campagne> page = campagneser.listAll(i);
		long totalItems  = page.getTotalElements();
		int totalPages  = page.getTotalPages();
		List<Campagne> campagnes = page.getContent();
		
		if (principal==null) 
		{ 		
			model.addAttribute("pagenum", currentPage);
			model.addAttribute("campagnes", campagnes);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", totalPages);
	        return "all_campagne";
			
		}
		else if(AdminRepository.selectImage(principal.getName())!= null  ){
			String image = AdminRepository.selectImage(principal.getName());
			System.out.println("image admin:" + image);
			model.addAttribute("image", image);
		}
		
		else if(DonneurRepository.selectImage(principal.getName())!= null){
			String image = DonneurRepository.selectImage(principal.getName());
			System.out.println("donneur image:" + image);
			model.addAttribute("image", image);
		}
		
		else if(HopitalRepository.selectImage(principal.getName())!= null){
			String image = HopitalRepository.selectImage(principal.getName());
			System.out.println("hopital image:" + image);
			model.addAttribute("image", image);
		}
		
		model.addAttribute("pagenum", currentPage);
		model.addAttribute("campagnes", campagnes);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
        return "all_campagne";
    }
	
    @PostMapping("/addcampagne")
    public String addcampagne(@Valid Campagne campagne, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "all_campagne";
        }

        CampagneRepository.save(campagne);
        model.addAttribute("campagnes", CampagneRepository.findAll());
        return "redirect:/all_campagne";
    }
    
    @GetMapping("/editcampagne/{id_campagne}")
    public String showupdateCampagne(@PathVariable("id_campagne") int id_campagne, Model model) {
    	Campagne campagne = CampagneRepository.findById(id_campagne)
          .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id_campagne));

        model.addAttribute("campagne", campagne);
        return "update_campagne";
    }
   
    @PostMapping("/updatecampagne/{id_campagne}")
    public String updateCampagne(@PathVariable("id_campagne") int id_campagne, @Valid Campagne campagne,
      BindingResult result, Model model) {
        if (result.hasErrors()) {
        	campagne.setId_campagne(id_campagne);
            return "update_campagne";
        }

        CampagneRepository.save(campagne);
        model.addAttribute("campagnes", CampagneRepository.findAll());
        return "redirect:/all_campagne";
    }
    
    @GetMapping("/deletecampagne/{id_campagne}")
    public String deleteCampagne(@PathVariable("id_campagne") int id_campagne, Model model) {
    	Campagne campagne = CampagneRepository.findById(id_campagne)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id_campagne));
    	CampagneRepository.delete(campagne);
        model.addAttribute("campagnes", CampagneRepository.findAll());
        return "redirect:/all_campagne";
    }
    
    
    
    
}