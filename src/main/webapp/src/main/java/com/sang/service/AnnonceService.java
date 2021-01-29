package com.sang.service;


import java.util.ArrayList;  
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import com.sang.model.Annonce;  
import com.sang.repository.AnnonceRepository;  
//defining the business logic  
@Service  
public class AnnonceService   
{  
@Autowired  
AnnonceRepository annonceRepository;  

//getting all Annonce record by using the method findaAll() of CrudRepository  
public List<Annonce> getAllAnnonce()   
{  
List<Annonce> annonce = new ArrayList<Annonce>();  
annonceRepository.findAll().forEach(annonce1 -> annonce.add(annonce1));  
return annonce;  
} 

//getting a specific record by using the method findById() of CrudRepository  
public Annonce getAnnonceById(int id)   
{  
return annonceRepository.findById(id).get();  
} 

//saving a specific record by using the method save() of CrudRepository  
public void saveOrUpdate(Annonce Annonce)   
{  
annonceRepository.save(Annonce);  
}  

//deleting a specific record by using the method deleteById() of CrudRepository  
public void delete(int id_annonce)   
{  
annonceRepository.deleteById(id_annonce);  
}  

//updating a record  
public void update(Annonce Annonce, int id_annonce)   
{  
annonceRepository.save(Annonce);  
}  
}  