package com.sena.solution.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sena.solution.controllers.views.DocumentoView;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.models.ParroquiaAcgPK;
import com.sena.solution.services.AlmacenamientoService;
import com.sena.solution.services.ArchivoCategoriaGeneralService;
import com.sena.solution.services.DocumentoService;
import com.sena.solution.services.ParroquiaAcgService;
import com.sena.solution.services.ParroquiaService;

@Controller
@RequestMapping("/documento")
public class DocumentoController {

  @Autowired
  private DocumentoService documentoService;

  @Autowired
  private ParroquiaAcgService parroquiaAcgService;

  @Autowired
  private ParroquiaService parroquiaService;

  @Autowired
  private ArchivoCategoriaGeneralService acgService;

  @Autowired
  private AlmacenamientoService almacenamientoService;

  
  

  @GetMapping("/listar/{idParroquia}/{idAcg}")
  public ModelAndView listarDocumentos(@PathVariable("idParroquia") Long idParroquia,
      @PathVariable("idAcg") Long idAcg) {

    ModelAndView modelAndView = new ModelAndView(DocumentoView.LISTD);
    ParroquiaAcg parroquiaAcg = parroquiaAcgService.buscarPorIdParroquiaAcg(new ParroquiaAcgPK(idParroquia, idAcg));
    modelAndView.addObject("ListaDocumentos", documentoService.encontrarDocumentosPorParroquiaAcg(parroquiaAcg));

    modelAndView.addObject("parroquia", parroquiaService.buscarPorIdParroquia(idParroquia));
    modelAndView.addObject("acg", acgService.buscarPorIdACG(idAcg));
    return modelAndView;
  }

  @GetMapping("/formularioAgregar/{idParroquia}/{idAcg}")
  public ModelAndView formularioAgregarDocumento(@PathVariable("idParroquia") Long idParroquia,
	      @PathVariable("idAcg") Long idAcg) {

    ModelAndView modelAndView = new ModelAndView(DocumentoView.FORMD);
    modelAndView.addObject("parroquia", parroquiaService.buscarPorIdParroquia(idParroquia));
    modelAndView.addObject("acg", acgService.buscarPorIdACG(idAcg));

    return modelAndView;
  }

  @PostMapping("/agregar/{idParroquia}/{idAcg}")
  public String agregarDocumento(@ModelAttribute("file")MultipartFile file,@PathVariable("idParroquia") Long idParroquia,
	      @PathVariable("idAcg") Long idAcg){
    ParroquiaAcg parroquiaAcg = parroquiaAcgService.buscarPorIdParroquiaAcg(new ParroquiaAcgPK(idParroquia, idAcg));
    try{
      almacenamientoService.guardarDocumento(file,parroquiaAcg);
      return "redirect:/documento/listar/" + String.valueOf(idParroquia) + "/" + String.valueOf(idAcg) ;
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return null;

  }
  
}
