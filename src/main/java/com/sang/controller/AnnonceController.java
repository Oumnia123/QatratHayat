package com.sang.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
 
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sang.model.Annonce;

import com.sang.repository.AdminRepository;
import com.sang.repository.AnnonceRepository;
import com.sang.repository.ArticleRepository;
import com.sang.repository.DonneurRepository;
import com.sang.repository.HopitalRepository;
import com.sang.service.AdminService;
import com.sang.service.AnnonceService;
import com.sang.service.DonneurService;
import com.sang.service.HopitalService;



//mark class as Controller  
@Controller  
public class AnnonceController   
{  
	@Autowired
	AnnonceRepository AnnonceRepository;
	
	
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
	AnnonceService annonceServ ;

	@GetMapping("/all_annonce")
    public String showAllAnnonce(Annonce annonce,Model model, Principal principal, Authentication authentication) {
		
		int currentPage = 1;
		Page<Annonce> page = annonceServ.listAll(currentPage);
		long totalItems  = page.getTotalElements();
		int totalPages  = page.getTotalPages();
		List<Annonce> annonces = new ArrayList<Annonce>();

		annonces = page.getContent();
		
		
		
		if (principal==null) 
		{
			System.out.println("Rien faire");
			model.addAttribute("pagenum", currentPage);
			model.addAttribute("annonces", annonces);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", totalPages);
			return "all_annonce"; 
			
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
		
		
		
		/*String role = authentication.getAuthorities().toString();
		
		System.out.println(role);
		if(role== "[DONNEUR]") {
			System.out.println("je suis un DONNEUR");
		}
		if(role=="[ADMIN]") {
			System.out.println("je suis un ADMIN");
		}
		if(role=="[HOPITAL]") {
			System.out.println("je suis un HOPITAL");
		}
		*/
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DONNEUR"))) {
			System.out.println("je suis un DONNEUR");
			String Groupe = DonneurRepository.selectGroupeD(principal.getName());
			
			Page<Annonce> pagee = annonceServ.listAllWIthQuery(currentPage,Groupe);
			long totalItemss  = pagee.getTotalElements();
			int totalPagess  = pagee.getTotalPages();
			
			annonces = pagee.getContent();
			

			model.addAttribute("pagenum", currentPage);
			model.addAttribute("annonces", annonces);
			model.addAttribute("totalItems", totalItemss);
			model.addAttribute("totalPages", totalPagess);
			model.addAttribute("tousounon", "Afficher toutes les annonces");
			return "all_annonce";
		
	
		}
		model.addAttribute("pagenum", currentPage);
		model.addAttribute("annonces", annonces);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
        return "all_annonce";
    }
	
	@GetMapping("/showAllAnnonces")
    public String showAllAnnonceee(Annonce annonce,Model model, Principal principal, Authentication authentication) {
		
		int currentPage = 1;
		Page<Annonce> page = annonceServ.listAll(currentPage);
		long totalItems  = page.getTotalElements();
		int totalPages  = page.getTotalPages();
		List<Annonce> annonces = new ArrayList<Annonce>();

		annonces = page.getContent();
		
		
		
		if (principal==null) 
		{
			System.out.println("Rien faire");
			model.addAttribute("pagenum", currentPage);
			model.addAttribute("annonces", annonces);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", totalPages);
			return "all_annonce"; 
			
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
		
		
		
		/*String role = authentication.getAuthorities().toString();
		
		System.out.println(role);
		if(role== "[DONNEUR]") {
			System.out.println("je suis un DONNEUR");
		}
		if(role=="[ADMIN]") {
			System.out.println("je suis un ADMIN");
		}
		if(role=="[HOPITAL]") {
			System.out.println("je suis un HOPITAL");
		}
		*/
		
		model.addAttribute("pagenum", currentPage);
		model.addAttribute("annonces", annonces);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("tousounon", "Afficher les annonces qui conviennent à mon groupe sanguin");
        return "all_annonce";
    }
	
	@GetMapping("/all_annonce/{i}")
    public String showAllannoncee(Annonce annonce,Model model, Principal principal, @PathVariable("i") int i) {
		
		int currentPage = i;
		Page<Annonce> page = annonceServ.listAll(i);
		long totalItems  = page.getTotalElements();
		int totalPages  = page.getTotalPages();
		List<Annonce> annonces = page.getContent();
		
		if (principal==null) 
		{ 		
			model.addAttribute("pagenum", currentPage);
			model.addAttribute("annonces", annonces);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", totalPages);
	        return "all_annonce";
			
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
		model.addAttribute("annonces", annonces);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
        return "all_annonce";
    }
	
    @PostMapping("/addannonce")
    public String addAnnonce(@Valid Annonce annonce, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "all_annonce";
        }

        AnnonceRepository.save(annonce);
        model.addAttribute("annonces", AnnonceRepository.findAll());
        return "redirect:/all_annonce";
    }
    
    @GetMapping("/editannonce/{id_annonce}")
    public String showupdateAnnonce(@PathVariable("id_annonce") int id_annonce, Model model) {
        Annonce annonce = AnnonceRepository.findById(id_annonce)
          .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id_annonce));

        model.addAttribute("annonce", annonce);
        return "update_annonce";
    }
   
    @PostMapping("/updateannonce/{id_annonce}")
    public String updateAnnonce(@PathVariable("id_annonce") int id_annonce, @Valid Annonce annonce,
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            annonce.setId_annonce(id_annonce);
            return "update_annonce";
        }

        AnnonceRepository.save(annonce);
        model.addAttribute("annonces", AnnonceRepository.findAll());
        return "redirect:/all_annonce";
    }
    
    @GetMapping("/deleteannonce/{id_annonce}")
    public String deleteAnnonce(@PathVariable("id_annonce") int id_annonce, Model model) {
        Annonce annonce = AnnonceRepository.findById(id_annonce)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id_annonce));
        AnnonceRepository.delete(annonce);
        model.addAttribute("annonces", AnnonceRepository.findAll());
        return "redirect:/all_annonce";
    }
    
/*
    @GetMapping("/test/{grp_s}")
    public String selectAnnonces(Model model, @RequestParam String grp_s) {

        model.addAttribute("annoncesS", (List<Annonce>) AnnonceService.selectAllAnnonceG(grp_s));

        return "redirect:/testt";
    }
    

    @RequestMapping("/testt")
    public Map<String, Object> home(Principal principal) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello " + principal.getName());
        return model;
    }
    */
   
    
}