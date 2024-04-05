package com.sena.solution;


import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.sena.solution.models.Curia;
import com.sena.solution.models.Parroquia;
import com.sena.solution.models.RolEntity;
import com.sena.solution.models.RolEnum;
import com.sena.solution.models.Usuario;
import com.sena.solution.models.Vicaria;
import com.sena.solution.repositories.ArchivoCategoriaGeneralRepository;
import com.sena.solution.repositories.CuriaRepository;
import com.sena.solution.repositories.ParroquiaAcgRepository;
import com.sena.solution.repositories.ParroquiaRepository;
import com.sena.solution.repositories.RolRepository;
import com.sena.solution.repositories.UsuarioRepository;
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
		
	    //crearDirectorio();
	   /* Curia curia1 = new Curia();
	    if(!curiaRepository.existsById(Long.valueOf(1))) {
	    	curia1.setId(1L);
		    curia1.setNombre("Málaga-Soata");
		    curia1.setCiudad("Málaga");
		    curia1.setDireccion("Calle 00 #00-00 Barrio");
		    curia1.setEmail("curia@gmail.com");
		    curia1.setTelefono("3222212144");
		    
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

	    Parroquia parroquia1 = new Parroquia(1L, "Santisima Trinidad", "Calle 00 #00-00 Barrio", "Málaga", "3324324324", "parroquia@gmail.com", vicariaRepository.getById(1L));
			if (!parroquiaRepository.existsByNombre("Santisima Trinidad")) {
				parroquiaRepository.save(parroquia1);
			}
			
			ParroquiaAcgPK clave = new ParroquiaAcgPK(parroquia1.getId(), acg1.getIdACG());
			ParroquiaAcgPK clave2 = new ParroquiaAcgPK(parroquia1.getId(), acg2.getIdACG());
			ParroquiaAcg parroquiaAcg = new ParroquiaAcg(clave, parroquia1, acg1);
			ParroquiaAcg parroquiaAcg2 = new ParroquiaAcg(clave2,parroquia1, acg2);


			parroquiaAcgRepository.save(parroquiaAcg);
			parroquiaAcgRepository.save(parroquiaAcg2);*/
	    
	    
	}	

	/*public static void crearDirectorio() {
		Logger logger = LoggerFactory.getLogger(GestorDocumentalApplication.class);
		final String dir = "src/main/resources/archivos";
			
		try {
			Path path = Paths.get(dir);
			Files.createDirectories(path);
			logger.info("el directorio ha sido creado");
		} catch (Exception e) {
			logger.info("el directorio a: "+ e.getMessage());
		}

	}*/
	
	@Bean
	CommandLineRunner init(RolRepository rolRepository,
			CuriaRepository curiaRepository, 
			VicariaRepository vicariaRepository, 
			ParroquiaRepository parroquiaRepository,
			UsuarioRepository usuarioRepository) {
		return arg ->{
			RolEntity rolAdmin = new RolEntity(1L, RolEnum.ADMIN);
			RolEntity rolParroco = new RolEntity(2L, RolEnum.PARROCO);
			RolEntity rolSecretaria = new RolEntity(3L, RolEnum.SECRETARIA);
			RolEntity rolContador = new RolEntity(4L, RolEnum.CONTADOR);
			
			rolRepository.saveAll(List.of(rolAdmin,rolParroco,rolSecretaria,rolContador));
			
			Curia curiaPrueba = new Curia(1L, "curiaPrueba", "Calle 00 #00-00 Barrio", "Malaga", "3123453654", "curiaPrueba@gmail.com");
			curiaRepository.save(curiaPrueba);
			
			Vicaria vicariaPrueba = new Vicaria(1L, "vicariaPrueba", curiaPrueba);
			vicariaRepository.save(vicariaPrueba);
			
			Parroquia parroquiaPrueba = new Parroquia(1L, "parroquiaPrueba", "Calle 00 #00-00 Barrio", "cuidadPrueba", "3123456879", "parroquiaPrueba@gmail.com", vicariaPrueba);
			parroquiaRepository.save(parroquiaPrueba);
			
			Set<RolEntity> roles = Set.of(rolAdmin,rolParroco);
			Usuario usuarioPrueba = new Usuario(1L, "nombrePrueba", "apellidoPrueba", "23243734", "3125569747", "usuarioPrueba@gmail.com", roles, parroquiaPrueba);
			usuarioPrueba.setUsername("iwi");
			usuarioPrueba.setPassword("Aa*4245sd");
			usuarioRepository.save(usuarioPrueba);
		};
	}

}
