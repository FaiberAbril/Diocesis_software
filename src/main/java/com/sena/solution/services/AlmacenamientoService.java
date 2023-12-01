package com.sena.solution.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sena.solution.models.Documento;
import com.sena.solution.models.ParroquiaAcg;

@Service
public class AlmacenamientoService {
  
 
  @Autowired
  private DocumentoService documentoService;

  //"src/main/resources/archivos"
  private final String CARPETA_PATH = System.getProperty("user.dir") + "/src/main/resources/archivos/";
  

  public boolean guardarDocumento(MultipartFile file, ParroquiaAcg parroquiaAcg) throws IllegalStateException, IOException {
	  
    String archivo_path = CARPETA_PATH + file.getOriginalFilename();
    if(!documentoService.existeDocumentoPorNombre(file.getOriginalFilename())){
      Documento documento = new Documento();
      documento.setNombreDocumento(file.getOriginalFilename());
      documento.setTipo(file.getContentType());
      documento.setPath(archivo_path);
      documento.setParroquiaAcg(parroquiaAcg);
      documentoService.guardarDocumento(documento);
      file.transferTo(new File(archivo_path));
      return true;
    }else{
      return false;
    }
    

  }

  public byte[] descargarDocumento(String fileNombre) throws IOException {
    Optional<Documento> opDocumento = documentoService.encontrarDocumentoPorNombre(fileNombre);
    if (opDocumento.isPresent()) {
      String filePath = opDocumento.get().getPath();
      byte[] file = Files.readAllBytes(new File(filePath).toPath());
      return file;
    }
    
       return null;
  }
  
  public boolean eliminarDocumento(String filename, Long idDocumento) {
	  Path root = Paths.get(CARPETA_PATH);
	  try{
		Path file = root.resolve(filename);
		documentoService.eliminarDocumento(idDocumento);
		return Files.deleteIfExists(file);
		 }catch (IOException e) {
			 throw new RuntimeException("Error: El archivo no existe" + e.getMessage());
			 
		 }
	  
	  
  }

}
