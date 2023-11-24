package com.sena.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.sena.solution.models.ArchivoCategoriaGeneral;
import com.sena.solution.models.Curia;
import com.sena.solution.models.Parroquia;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.models.ParroquiaAcgPK;
import com.sena.solution.models.Vicaria;
import com.sena.solution.repositories.ArchivoCategoriaGeneralRepository;
import com.sena.solution.repositories.CuriaRepository;
import com.sena.solution.repositories.ParroquiaAcgRepository;
import com.sena.solution.repositories.ParroquiaRepository;
import com.sena.solution.repositories.VicariaRepository;

@SpringBootApplication
public class GestorDocumentalApplication {
	
	private static CuriaRepository curiaRepository;
	
	private static VicariaRepository vicariaRepository;
	
	private static ParroquiaRepository parroquiaRepository;

	private static ArchivoCategoriaGeneralRepository aCGRepository;

	private static ParroquiaAcgRepository parroquiaAcgRepository;

	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:validationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
		public LocalValidatorFactoryBean validator() {
			LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
			bean.setValidationMessageSource(messageSource());
			return bean;
		}
		
    public  GestorDocumentalApplication(CuriaRepository curiaRepository, VicariaRepository vicariaRepository, ParroquiaRepository parroquiaRepository, ArchivoCategoriaGeneralRepository aCGRepository, ParroquiaAcgRepository parroquiaAcgRepository) {
        GestorDocumentalApplication.curiaRepository = curiaRepository;
        GestorDocumentalApplication.vicariaRepository = vicariaRepository;
				GestorDocumentalApplication.parroquiaRepository = parroquiaRepository;
				GestorDocumentalApplication.aCGRepository = aCGRepository;
				GestorDocumentalApplication.parroquiaAcgRepository = parroquiaAcgRepository;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(GestorDocumentalApplication.class, args);
		
	    
	    Curia curia1 = new Curia();
	    if(!curiaRepository.existsById(Long.valueOf(1))) {
	    	curia1.setId(1L);
		    curia1.setNombre("Málaga-Soata");
		    curia1.setCiudad("Málaga");
		    curia1.setDireccion("cra");
		    curia1.setEmail("curia@gmail.com");
		    curia1.setTelefono("32222121");
		    
		    curiaRepository.save(curia1);
	    }
	    
	    Vicaria vicaria1 = new Vicaria(1L, "Santo Tomas Apostol", curiaRepository.getById(1L));
	    if(!vicariaRepository.existsByNombreVicaria("Santo Tomas Apostol")){
	    	vicariaRepository.save(vicaria1);
	    }
	    
			ArchivoCategoriaGeneral acg1 = new ArchivoCategoriaGeneral(1l, "SSG");
			ArchivoCategoriaGeneral acg2 = new ArchivoCategoriaGeneral(2l, "CGB");
			aCGRepository.save(acg1);
			aCGRepository.save(acg2);

	    Parroquia parroquia1 = new Parroquia(1L, "Santisima Trinidad", "cra", "Málaga", "3324324324", "parroquia@gmail.com", vicariaRepository.getById(1L));
			if (!parroquiaRepository.existsByNombre("Santisima Trinidad")) {
				parroquiaRepository.save(parroquia1);
			}
			
			ParroquiaAcgPK clave = new ParroquiaAcgPK(parroquia1.getId(), acg1.getIdACG());
			ParroquiaAcgPK clave2 = new ParroquiaAcgPK(parroquia1.getId(), acg2.getIdACG());
			ParroquiaAcg parroquiaAcg = new ParroquiaAcg(clave, parroquia1, acg1);
			ParroquiaAcg parroquiaAcg2 = new ParroquiaAcg(clave2,parroquia1, acg2);


			parroquiaAcgRepository.save(parroquiaAcg);
			parroquiaAcgRepository.save(parroquiaAcg2);
	    
	    
	}	

}
