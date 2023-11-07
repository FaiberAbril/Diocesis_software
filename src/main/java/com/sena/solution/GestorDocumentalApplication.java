package com.sena.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sena.solution.models.Curia;
import com.sena.solution.models.Parroquia;
import com.sena.solution.models.Vicaria;
import com.sena.solution.repositories.CuriaRepository;
import com.sena.solution.repositories.ParroquiaRepository;
import com.sena.solution.repositories.VicariaRepository;
import com.sena.solution.services.CuriaService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@SpringBootApplication
public class GestorDocumentalApplication {
	
	@Autowired
	private static CuriaRepository curiaRepository;
	
	private static VicariaRepository vicariaRepository;
	
	private static ParroquiaRepository parroquiaRepository;
	
	
    public  GestorDocumentalApplication(CuriaRepository curiaRepository, VicariaRepository vicariaRepository, ParroquiaRepository parroquiaRepository) {
        GestorDocumentalApplication.curiaRepository = curiaRepository;
        GestorDocumentalApplication.vicariaRepository = vicariaRepository;
        GestorDocumentalApplication.parroquiaRepository = parroquiaRepository;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(GestorDocumentalApplication.class, args);
		
	    
	    Curia curia1 = new Curia();
	    if(!curiaRepository.existsById(Long.valueOf(1))) {
	    	curia1.setId(Long.valueOf(1));
		    curia1.setNombre("Málaga-Soata");
		    curia1.setCiudad("Málaga");
		    curia1.setDireccion("cra");
		    curia1.setEmail("curia@gmail.com");
		    curia1.setTelefono("32222121");
		    
		    curiaRepository.save(curia1);
	    }
	    
	    Vicaria vicaria1 = new Vicaria(Long.valueOf(1), "Santo Tomas Apostol", curiaRepository.getById(Long.valueOf(1)));
	    if(!vicariaRepository.existsByNombreVicaria("Santo Tomas Apostol")){
	    	vicariaRepository.save(vicaria1);
	    }
	    
	    Parroquia parroquia1 = new Parroquia(Long.valueOf(1), "Santisima Trinidad", "cra", "Málaga", "3324324324", "parroquia@gmail.com", vicariaRepository.getById(Long.valueOf(2)));
	    if(!parroquiaRepository.existsByNombre("Santisima Trinidad")) {
	    	parroquiaRepository.save(parroquia1);
	    }
	    
	    
	}	

}
