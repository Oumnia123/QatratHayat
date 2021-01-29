package com.sang.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sang.model.Admin;
import com.sang.model.Annonce;
import com.sang.model.Article;
import com.sang.model.Donneur;
import com.sang.model.Hopital;
import com.sang.repository.AdminRepository;
import com.sang.repository.DonneurRepository;
import com.sang.repository.HopitalRepository;
import com.sang.service.AdminService;
import com.sang.service.DonneurService;
import com.sang.service.HopitalService;


@Controller
public class AuthentificationController {
	
	@Autowired
	DonneurService donneurService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	HopitalService hopitalService;

	@Autowired
	AdminService adminService;
	
	@Autowired
	AdminRepository AdminRepository;
	
	@Autowired
	DonneurRepository DonneurRepository;
	
	@Autowired
	HopitalRepository HopitalRepository;
	
	
    private final String UPLOAD_DIR = "./src/main/resources/static/photo_de_profiles/";

	@ResponseBody
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}
	@GetMapping("/logout")
	public String logout()
	{
		return "login";
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
	@RequestMapping(value = "/registeradmin", method = RequestMethod.GET)
	public ModelAndView registeradmin() {
		ModelAndView modelAndView = new ModelAndView();
		Admin admin = new Admin();
		modelAndView.addObject("admin", admin);
		modelAndView.setViewName("registeradmin"); // resources/template/registeradmin.html
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
	public ModelAndView home(Principal p) {
		ModelAndView modelAndView = new ModelAndView();
		if (p==null) 
		{
			System.out.println("Rien faire");
			modelAndView.setViewName("home"); // resources/template/home.html
			return modelAndView;
		}
		else if(AdminRepository.selectImage(p.getName())!= null  ){
			String image = AdminRepository.selectImage(p.getName());
			System.out.println("image admin:" + image);
			modelAndView.addObject("image", image);
		}
		
		else if(DonneurRepository.selectImage(p.getName())!= null){
			String image = DonneurRepository.selectImage(p.getName());
			System.out.println("donneur image:" + image);
			modelAndView.addObject("image", image);
		}
		
		else if(HopitalRepository.selectImage(p.getName())!= null){
			String image = HopitalRepository.selectImage(p.getName());
			System.out.println("hopital image:" + image);
			modelAndView.addObject("image", image);
		}
		
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
/*
	@ResponseBody
	@RequestMapping("/blog")
	public ModelAndView blog() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("blog"); // resources/template/blog.html
		return modelAndView;
	}
*/
	@ResponseBody
	@RequestMapping("/contact")
	public ModelAndView contact(Principal p) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (p==null) 
		{
			System.out.println("Rien faire");
			modelAndView.setViewName("contact"); 
			return modelAndView;
		}
		else if(AdminRepository.selectImage(p.getName())!= null  ){
			String image = AdminRepository.selectImage(p.getName());
			System.out.println("image admin:" + image);
			modelAndView.addObject("image", image);
		}
		
		else if(DonneurRepository.selectImage(p.getName())!= null){
			String image = DonneurRepository.selectImage(p.getName());
			System.out.println("donneur image:" + image);
			modelAndView.addObject("image", image);
		}
		
		else if(HopitalRepository.selectImage(p.getName())!= null){
			String image = HopitalRepository.selectImage(p.getName());
			System.out.println("hopital image:" + image);
			modelAndView.addObject("image", image);
		}
		modelAndView.setViewName("contact"); // resources/template/contact.html
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/registerdonneur", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid Donneur donneur, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("erreurs", "SVP correctez les erreurs du formulaire");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelAndView.setViewName("registerdonneur");
			return modelAndView;
		}
		else if(donneurService.isUserAlreadyPresent(donneur)){
			modelAndView.addObject("donneurExiste", " Ce donneur existe déjà!");			
		}
		// we will save the user if, no binding errors
		else {
			
			donneurService.saveUser(donneur);
			modelAndView.addObject("successMessage", " Votre compte a été créé avec succès!");
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
			modelAndView.addObject("erreurs", "SVP correctez les erreurs du formulaire");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelAndView.setViewName("registerhopital");
			return modelAndView;
		}
		else if(hopitalService.isUserAlreadyPresent(hopital)){
			modelAndView.addObject("hopitalExiste", "Cet hôpital existe déjà!");			
		}
		// we will save the user if, no binding errors
		else {
			hopitalService.saveUser(hopital);
			modelAndView.addObject("successMessage", " Votre compte a été créé avec succès!");
		}
		modelAndView.addObject("hopital", new Hopital());
		modelAndView.setViewName("registerhopital");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/registeradmin", method=RequestMethod.POST)
	public ModelAndView registeradmin(@Valid Admin admin, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("erreurs", "SVP correctez les erreurs du formulaire");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelAndView.setViewName("registeradmin");
		}
		else if(adminService.isUserAlreadyPresent(admin)){
			modelAndView.addObject("adminExiste", "Cet Admin existe déjà!");
			modelAndView.setViewName("registeradmin");
		}
		// we will save the user if, no binding errors
		else {
			adminService.saveUser(admin);
			modelAndView.addObject("successMessage", "Votre compte a été créé avec succès!");
		}
		modelAndView.addObject("admin", new Admin());
		modelAndView.setViewName("registeradmin");
		return modelAndView;
	}
	
	@GetMapping("/profile")
    public String Showuserdetails(Model model, Principal principal) {
		
	   
       model.addAttribute("AdminDetails",  AdminRepository.findByUsername( principal.getName()));
       model.addAttribute("DonneurDetails", DonneurRepository.findByUsername( principal.getName()));
       model.addAttribute("HopitalDetails", HopitalRepository.findByUsername( principal.getName()));
       
       if(AdminRepository.selectImage(principal.getName())!= null){
			String image = AdminRepository.selectImage(principal.getName());
			System.out.println(image);
			 model.addAttribute("image", image);
		}
		
		if(DonneurRepository.selectImage(principal.getName())!= null){
			String image = DonneurRepository.selectImage(principal.getName());
			System.out.println(image);
			 model.addAttribute("image", image);
		}
		
		if(HopitalRepository.selectImage(principal.getName())!= null){
			String image = HopitalRepository.selectImage(principal.getName());
			System.out.println(image);
			 model.addAttribute("image", image);
		}

        return "profile";
    }
	
	 @GetMapping("/updateadmin")
	    public String showupdateadmin() {
	    	
	        return "updateadmin";
	    }
	 
	 
	
	 @PostMapping("/updateadmin")
	    public String updateadmin(@RequestParam("nom") String nom,@RequestParam("prenom") String prenom,
	       Model model, Principal principal) {
	       
	        	
	        	
	        	System.out.println(nom);
	        	System.out.println(prenom);
	   
	        	AdminRepository.updateNameAndLastName(principal.getName(), nom, prenom);
	 	        model.addAttribute("AdminDetails", AdminRepository.findByUsername( principal.getName()));
	 	       
	       
	        
	        return "redirect:/profile";
	    }
	 
	 @GetMapping("/updateadminpwd")
	    public String showupdateadminpwd( Model model) {
	    	
	        return "updateadminpwd";
	    }
	 
	 
	 @PostMapping("/updateadminpwd")
	    public String showupdateadminpwda(@RequestParam("password") String password, Principal principal) {
		 
		 String pwd =bCryptPasswordEncoder.encode(password);
		    
				System.out.println("no hash :" + password);
				System.out.println("with hash :" + pwd);
				AdminRepository.updatePwd(principal.getName(), pwd);
				return "redirect:/profile";
			
	        
	    }
	 
	 @PostMapping("/addAdminImage")
	    public String uploadFile(@RequestParam("file") MultipartFile file,Principal principal, RedirectAttributes attributes, Model model) {

	    
	        // check if file is empty
	        if (file.isEmpty()) {
	            attributes.addFlashAttribute("message", "SVP selectionnez un fichier ");
	            return "redirect:/profile";
	        }

	        // normalize the file path
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        // save the file on the local file system
	        try {
	            Path path = Paths.get(UPLOAD_DIR + fileName);
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
	        String imagepath = fileName;
	        
	        System.out.println("OK!");
	        AdminRepository.updateImage(principal.getName(), imagepath);
	        
	        
	        
	        // return success response
	        
	        
	        return "redirect:/profile";
	    }
	 
	 
	 
	 @GetMapping("/updatehopital")
	    public String showupdatehop(Model model,Principal principal) {
		 
		String nom = HopitalRepository.selectnom(principal.getName());
		String tele = HopitalRepository.selecttele(principal.getName());
		String lieu = HopitalRepository.selectlieu(principal.getName());
		 
		
		System.out.println(nom);
		System.out.println(tele);
		System.out.println(lieu);
		
		model.addAttribute("nom", nom);	
		
		model.addAttribute("tele", tele);
		
		model.addAttribute("lieu", lieu);

	        return "/updatehopital";
	    }
	 
	 
	
	 @PostMapping("/updatehopital")
	    public String updatehopital(@RequestParam("nom") String nom,@RequestParam("tele") String tele,@RequestParam("lieu") String lieu,
	       Model model, Principal principal) {
	      
	        	HopitalRepository.updateInoHop(principal.getName(), nom, tele, lieu);
	 	        model.addAttribute("HopitalDetails", HopitalRepository.findByUsername( principal.getName()));
	 	        
	 	        return "redirect:/profile";
	    }
	 
	 @GetMapping("/updatehopitalpwd")
	    public String showupdatehoppwd( Model model) {
	    	
	        return "updatehopitalpwd";
	    }
	 
	 
	 @PostMapping("/updatehopitalpwd")
	    public String showupdatehoppwda(@RequestParam("password") String password, Principal principal) {
		 
		 String pwd =bCryptPasswordEncoder.encode(password);
		    
				System.out.println("no hash :" + password);
				System.out.println("with hash :" + pwd);
				HopitalRepository.updateHPwd(principal.getName(), pwd);
				return "redirect:/profile";
			
	        
	    }
	 
	 @PostMapping("/addHopitalImage")
	    public String uploadFile2(@RequestParam("file") MultipartFile file,Principal principal, RedirectAttributes attributes, Model model) {

	    
	        // check if file is empty
	        if (file.isEmpty()) {
	            attributes.addFlashAttribute("message", "SVP selectionnez un fichier ");
	            return "redirect:/profile";
	        }

	        // normalize the file path
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        // save the file on the local file system
	        try {
	            Path path = Paths.get(UPLOAD_DIR + fileName);
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
	        String imagepath = fileName;
	        
	        System.out.println("OK!");
	        HopitalRepository.updateImage(principal.getName(), imagepath);
	        
	        
	        
	        // return success response
	        
	        
	        return "redirect:/profile";
	    }
	 
	 
	 
	 @GetMapping("/updatedonneur")
	    public String showupdateD(Model model,Principal principal) {
		 
		String prenom = DonneurRepository.selectprenomD(principal.getName());
		String nom = DonneurRepository.selectnomD(principal.getName());
		String grp = DonneurRepository.selectGroupeD(principal.getName());
		Date date = DonneurRepository.selectDateD(principal.getName());
		
		
		
		model.addAttribute("prenom", prenom);	
		
		model.addAttribute("nom", nom);
		
		model.addAttribute("grp", grp);
		
		model.addAttribute("date", date);

	        return "/updatedonneur";
	    }
	 
	 @PostMapping("/updatedonneur")
	    public String updateD(Model model,Principal principal,@RequestParam("prenom") String prenom,@RequestParam("nom") String nom,@RequestParam("grp") String grp,@RequestParam("date") Date date) {
		 
		
		System.out.println(prenom);
		System.out.println(nom);
		System.out.println(grp);
		System.out.println(date);
		
		DonneurRepository.updateinfoD(principal.getName(), prenom, nom, grp, date);
	        model.addAttribute("DonneurDetails", HopitalRepository.findByUsername( principal.getName()));
	        
	        return "redirect:/profile";

	        
	    }
	 
	 @GetMapping("/updatedonneurpwd")
	    public String showupdatedpwd( Model model) {
	    	
	        return "updatedonneurpwd";
	    }
	 
	 
	 @PostMapping("/updatedonneurpwd")
	    public String showupdatedpwda(@RequestParam("password") String password, Principal principal) {
		 
		 String pwd =bCryptPasswordEncoder.encode(password);
		    
				System.out.println("no hash :" + password);
				System.out.println("with hash :" + pwd);
				DonneurRepository.updateDPwd(principal.getName(), pwd);
				return "redirect:/profile";
			
	        
	    }
	 
	 @PostMapping("/addDonneurImage")
	    public String uploadFile3(@RequestParam("file") MultipartFile file,Principal principal, RedirectAttributes attributes, Model model) {

	    
	        // check if file is empty
	        if (file.isEmpty()) {
	            attributes.addFlashAttribute("message", "SVP selectionnez un fichier ");
	            return "redirect:/profile";
	        }

	        // normalize the file path
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        // save the file on the local file system
	        try {
	            Path path = Paths.get(UPLOAD_DIR + fileName);
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
	        String imagepath = fileName;
	        
	        System.out.println("OK!");
	        DonneurRepository.updateImageD(principal.getName(), imagepath);
	        
	        
	        
	        // return success response
	        
	        
	        return "redirect:/profile";
	    }
	 
	
	
}