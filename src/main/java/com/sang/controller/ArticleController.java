package com.sang.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sang.model.Annonce;
import com.sang.model.Article;
import com.sang.model.Admin;
import com.sang.repository.ArticleRepository;
import com.sang.repository.DonneurRepository;
import com.sang.repository.HopitalRepository;
import com.sang.repository.AdminRepository;
import com.sang.service.AdminService;
import com.sang.service.DonneurService;
import com.sang.service.HopitalService;


@Controller
public class ArticleController {
	

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

    private final String UPLOAD_DIR = "./src/main/resources/static/photos/";

    
    @GetMapping("/blog")
    public String showAllArticle(Article article,Model model, Principal principal ) {
    	
    	
    		if (principal==null) 
    		{
    			System.out.println("Rien faire");
    			model.addAttribute("articles", ArticleRepository.findAllByIdArticle());
    			return "blog"; 
    			
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
    		
		model.addAttribute("articles", ArticleRepository.findAllByIdArticle());
        return "blog";
    }
    
    @GetMapping("/success")
    public String success() {
		
        return "success";
    }
   
   
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,@Valid Article article,BindingResult result, Model model) {

    	 if (result.hasErrors()) {
             return "redirect:/blog";
         } 
    	 
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "SVP selectionnez un fichier ");
            return "redirect:/blog";
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
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        article.setImage(imagepath);
        article.setDate(timestamp);
        
        
        ArticleRepository.save(article);
        model.addAttribute("articles", ArticleRepository.findAllByIdArticle()); 
        
        // return success response
        attributes.addFlashAttribute("message", "vous avez ajouter un nouveau article avec succès ");
        
        return "redirect:/success";
    }
    
    
    @GetMapping("/editarticle/{id_article}")
    public String showupdateArticle(@PathVariable("id_article") int id_article, Model model) {
    	Article article = ArticleRepository.findById(id_article)
          .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id_article));
        model.addAttribute("article", article);
        return "update_article";
    }
   
    @PostMapping("/updatearticle/{id_article}")
    public String updateArticle(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,@PathVariable("id_article") int id_article, @Valid Article article,
      BindingResult result, Model model) {
        if (result.hasErrors()) {
        	article.setId_article(id_article);
            return "redirect:/update_article";
        }

     // check if file is empty
        if (file.isEmpty()) {
        	
        	 // normalize the file path
            
            
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            article.setDate(timestamp);
             
            ArticleRepository.save(article);
            model.addAttribute("articles", ArticleRepository.findAllByIdArticle());
            
        }
        else
        {
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
            
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            article.setImage(imagepath);
            article.setDate(timestamp);
             
            ArticleRepository.save(article);
            model.addAttribute("articles", ArticleRepository.findAllByIdArticle());
        	
        }
        // return success response
        attributes.addFlashAttribute("message", "vous avez modifier cet article avec succès ");
        
        return "redirect:/blog";
        
    }
   
    @GetMapping("/deletearticle/{id_article}")
    public String deleteArticle(@PathVariable("id_article") int id_article, Model model) {
        Article article = ArticleRepository.findById(id_article)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id_article));
        ArticleRepository.delete(article);
        model.addAttribute("articles", ArticleRepository.findAllByIdArticle());
        return "redirect:/blog";
    }
    
   
    
    
    
    
/*
 * 
 *  @GetMapping("/haha")
    public String getuser(Principal principal,Model model) {
        model.addAttribute("id", UUID.randomUUID().toString());
        model.addAttribute("content", "Hello " + principal.getName());
        return "haha";
    }
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET} )
   public String delete (int id_article)
   {
    	Article article = ArticleRepository.findById(id_article)
    	          .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id_article));
    	        ArticleRepository.delete(article);
    	        return "redirect:/blog";
    	
   }
   
    
    @GetMapping("/deleteArticle/{id}")
	public String deleteUser(Model model, @PathVariable("id_article") int id_article) {
		try {
			Article article = ArticleRepository.findById(id_article)
	    	          .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id_article));
	    	        ArticleRepository.delete(article);
		} catch (Exception e) {
			model.addAttribute("deleteError","The article could not be deleted.");
		}
		return "redirect:/blog";
	}
    */
    
    
    
    

}