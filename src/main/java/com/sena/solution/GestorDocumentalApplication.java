package com.sena.solution;


import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.sena.solution.models.ArchivoCategoriaGeneral;
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
@EnableScheduling
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
	}	

	
	@Bean
	CommandLineRunner init(RolRepository rolRepository,
			CuriaRepository curiaRepository, 
			VicariaRepository vicariaRepository, 
			ParroquiaRepository parroquiaRepository,
			UsuarioRepository usuarioRepository,
			ArchivoCategoriaGeneralRepository acgRepository) {
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
			Usuario usuarioPrueba = new Usuario(1L, "nombrePrueba", "apellidoPrueba", "23243734", "3125569747", "usuarioPrueba@gmail.com","iwi","Aa*4245sd", roles, parroquiaPrueba);
			usuarioPrueba.setEnabled(true);
			usuarioPrueba.setAccountNoExpired(true);
			usuarioPrueba.setAccountNoLocked(true);
			usuarioPrueba.setCredentialNoExpired(true);
			usuarioRepository.save(usuarioPrueba);
			
			
			acgRepository.save(new ArchivoCategoriaGeneral(1L, "archivo de prueba"));
		};
	}

}
