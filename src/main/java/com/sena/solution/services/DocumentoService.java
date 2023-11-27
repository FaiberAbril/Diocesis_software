package com.sena.solution.services;

import java.util.List;

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
}
