package com.sena.solution.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.solution.models.Documento;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.repositories.DocumentoRepository;

@Service
public class DocumentoService {

  @Autowired
  private DocumentoRepository documentoRepository;
  

  public List<Documento> encontrarDocumentosPorParroquiaAcg(ParroquiaAcg parroquiaAcg) {
    return documentoRepository.findByParroquiaAcg(parroquiaAcg);
  }  
  
  public Optional<Documento> encontrarDocumentoPorNombre(String nombreDocumento){
	  return documentoRepository.findByNombreDocumento(nombreDocumento);
  }
  public void eliminarDocumento(Long idDocumento) {
	  documentoRepository.deleteById(idDocumento);
  }
  
  public void guardarDocumento(Documento documento) {
	  documentoRepository.save(documento);
  }

  public boolean existeDocumentoPorNombre(String nombreDocumento){
    return documentoRepository.existsByNombreDocumento(nombreDocumento);
  }
}
