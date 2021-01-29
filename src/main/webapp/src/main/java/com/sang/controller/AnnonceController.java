package com.sang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;  
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sang.model.Annonce;
import com.sang.model.Donneur;
import com.sang.service.AnnonceService;  
//mark class as Controller  
@RestController  
public class AnnonceController   
{  
//autowire the annoncesService class  
@Autowired  
AnnonceService AnnonceService;  
//creating a get mapping that retrieves all the annonces detail from the database   

/*@GetMapping("/annonce")  
private List<Annonce> getAllAnnonce()   
{  
return AnnonceService.getAllAnnonce();  
} 
*/
@GetMapping("/annonce")
public ModelAndView getAllAnnonce() {

    List<Annonce> annonces =  AnnonceService.getAllAnnonce();

    Map<String, Object> params = new HashMap<>();
    params.put("annonce", annonces);

    return new ModelAndView("annonce", params);
}

//creating a get mapping that retrieves the detail of a specific annonce  
@GetMapping("/annonce/{id_annonce}")  
private Annonce getAnnonce(@PathVariable("id_annonce") int id_annonce)   
{  
return AnnonceService.getAnnonceById(id_annonce);  
}  
//creating a delete mapping that deletes a specified annonce  
@DeleteMapping("/annonce/{id_annonce}")  
private void deleteAnnonce(@PathVariable("id_annonce") int id_annonce)   
{  
AnnonceService.delete(id_annonce);  
}  
//creating post mapping that post the annonce detail in the database  
@ResponseBody
@RequestMapping(value = "/new_annonce", method = RequestMethod.GET)
public ModelAndView new_annonce() {
	
	ModelAndView modelAndView = new ModelAndView();
	Annonce annonce = new Annonce();
	modelAndView.addObject("annonce", annonce);
	modelAndView.setViewName("new_annonce"); 
	
	return modelAndView;
}

@PostMapping("/new_annonce")
public ModelAndView SaveAnnonce(@Valid Annonce annonce, BindingResult bindingResult, ModelMap modelMap)

{
	ModelAndView modelAndView = new ModelAndView();
	AnnonceService.saveOrUpdate(annonce);  
	modelAndView.addObject("successMessage", "annonce is registered successfully!");
	modelAndView.addObject("annonce", new Annonce());
	modelAndView.setViewName("new_annonce");
	
	return modelAndView;
}
/*
@PostMapping("/annonce")  
private int saveAnnonce(@RequestBody Annonce annonce)   
{  
AnnonceService.saveOrUpdate(annonce);  
return annonce.getId_annonce();  
} 
*/

//creating put mapping that updates the annonce detail   
@PutMapping("/annonce")  
private Annonce update(@RequestBody Annonce annonce)   
{  
AnnonceService.saveOrUpdate(annonce);  
return annonce;  
}  
}  