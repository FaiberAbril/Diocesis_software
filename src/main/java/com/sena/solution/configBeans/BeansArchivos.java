package com.sena.solution.configBeans;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeansArchivos {

  private Logger logger;

  @Bean
  public File crearDirectorioPrincipal() {
    logger = LoggerFactory.getLogger(BeansArchivos.class);
    final String dir = "src/main/resources/archivos";
    File tmpDir = new File(dir);
    if (!tmpDir.isDirectory()) {
      try {
        File f = Files.createDirectory(tmpDir.toPath()).toFile();
        logger.info("el directorio fue creado en : " + f.getAbsolutePath());
        return f;
      } catch (IOException e) {
        logger.info(e.getMessage());
      }
    } else {
      logger.info("el directorio principal ya existe");
    }
    return tmpDir;
  }
  
}
