package com.sena.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sena.solution.models.Curia;
import com.sena.solution.services.CuriaService;

@SpringBootApplication
public class GestorDocumentalApplication {
	

	
	
	public static void main(String[] args) {
		SpringApplication.run(GestorDocumentalApplication.class, args);
		
	}

}
