package com.sena.solution.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sena.solution.controllers.views.DocumentoView;
import com.sena.solution.models.Documento;
import com.sena.solution.models.ParroquiaAcg;
import com.sena.solution.models.ParroquiaAcgPK;
import com.sena.solution.services.AlmacenamientoService;
import com.sena.solution.services.ArchivoCategoriaGeneralService;
import com.sena.solution.services.DocumentoService;
import com.sena.solution.services.ParroquiaAcgService;
import com.sena.solution.services.ParroquiaService;


import jakarta.servlet.http.HttpServletResponse;

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
  
  
  private static final String DIRECCION = "/documento/listar/";


  private String msg = null;

  @GetMapping("/listar/{idParroquia}/{idAcg}")
  public ModelAndView listarDocumentos(@RequestParam(defaultValue = "0")int page, @PathVariable("idParroquia") Long idParroquia,
      @PathVariable("idAcg") Long idAcg,@RequestParam(name="msg",required = false) String msg,@Param("palabra")String palabra) { 
    ModelAndView modelAndView = new ModelAndView(DocumentoView.LISTD);
    ParroquiaAcg parroquiaAcg = parroquiaAcgService.buscarPorIdParroquiaAcg(new ParroquiaAcgPK(idParroquia, idAcg));
    modelAndView.addObject("url", DIRECCION+idParroquia+"/"+idAcg);
    modelAndView.addObject("palabra", palabra);
	modelAndView.addObject("currentPage", page);
	Pageable pg = PageRequest.of(page,5);
    Stream<Documento> streamDocumentos = documentoService.encontrarDocumentosPorParroquiaAcg(parroquiaAcg).stream(); 
    if(palabra != null) {
    	List<Documento> documentos = streamDocumentos.filter(d -> d.getNombreDocumento().contains(palabra)).toList();
    	modelAndView.addObject("ListaDocumentos", documentoService.paginacionDocumento(documentos, pg));
    	
    }else {
    	List<Documento> documentos = streamDocumentos.toList() ;
    	modelAndView.addObject("ListaDocumentos", documentoService.paginacionDocumento(documentos, pg) );
    }
    modelAndView.addObject("parroquia", parroquiaService.buscarPorIdParroquia(idParroquia));
    modelAndView.addObject("acg", acgService.buscarPorIdACG(idAcg));
    modelAndView.addObject("msg", msg);
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
    String nombreDocumento = file.getOriginalFilename();
    String message = "";
    try{
      boolean check = almacenamientoService.guardarDocumento(file, parroquiaAcg);
      if (check) {
        return "redirect:/documento/listar/" + String.valueOf(idParroquia) + "/" + String.valueOf(idAcg);
      }
      message = "el archivo ya existe: " + nombreDocumento;
      return "redirect:/documento/listar/" + String.valueOf(idParroquia) + "/" + String.valueOf(idAcg)+"?msg=" + message;
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return null;

  }

  @GetMapping("/vistaPrevia/{fileName}")
  @ResponseBody
  //HttpEntity<byte[]>
  public void  vistaPreviaDocumento(@PathVariable("fileName") String fileName, 
      HttpServletResponse response) throws IOException {
    
	Optional<Documento> opDocumento = documentoService.encontrarDocumentoPorNombre(fileName);
	String contentType = "";
	if(opDocumento.isPresent()) {
		contentType = opDocumento.get().getTipo();
	}
	
	/*byte[] documento = almacenamientoService.descargarDocumento(fileName);
	
	HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(documento.length);

    return new HttpEntity<byte[]>(documento, headers);*/

    InputStream in = new ByteArrayInputStream(almacenamientoService.descargarDocumento(fileName));
    //response.addHeader("Content-disposition", "attachment;filename=" + fileName);
    response.setContentType(contentType);//MediaType.APPLICATION_OCTET_STREAM_VALUE
    IOUtils.copy(in, response.getOutputStream());
    response.flushBuffer();

  }

  @GetMapping("/descargar/{fileName}")
  @ResponseBody
  //HttpEntity<byte[]>
  public void  descargarDocumento(@PathVariable("fileName") String fileName, 
      HttpServletResponse response) throws IOException {
    
	Optional<Documento> opDocumento = documentoService.encontrarDocumentoPorNombre(fileName);
	String contentType = "";
	if(opDocumento.isPresent()) {
		contentType = opDocumento.get().getTipo();
	}
	

    InputStream in = new ByteArrayInputStream(almacenamientoService.descargarDocumento(fileName));
    response.addHeader("Content-disposition", "attachment;filename=" + fileName);
    response.setContentType(contentType);//MediaType.APPLICATION_OCTET_STREAM_VALUE
    IOUtils.copy(in, response.getOutputStream());
    response.flushBuffer();

  }

  
  @GetMapping("/eliminar/{fileName}/{idDocumento}")
  public String eliminarDocumento(@PathVariable String fileName, @PathVariable Long idDocumento){
	  String message = "";
	  Documento documento = documentoService.encontrarDocumentoPorNombre(fileName).orElse(null);
	  
	  try {
		  boolean existed = almacenamientoService.eliminarDocumento(fileName,idDocumento);
		  
		  if (existed) {
			  message = "El archivo fue eliminado correctamente: " + fileName + idDocumento;
			  return"redirect:/documento/listar/" + String.valueOf(documento.getParroquiaAcg().getParroquia().getId())+"/"+ String.valueOf(documento.getParroquiaAcg().getAcg().getIdACG())+"?msg="+message;
			  
		  }
		     message = "El documento no existe!";
		     return"redirect:/documento/listar/" + String.valueOf(documento.getParroquiaAcg().getParroquia().getId())+"/"+ String.valueOf(documento.getParroquiaAcg().getAcg().getIdACG())+"?msg="+message;
		    } catch (Exception e) {
		    	message = e.getMessage();
		    	return"redirect:/documento/listar/" + String.valueOf(documento.getParroquiaAcg().getParroquia().getId())+"/"+ String.valueOf(documento.getParroquiaAcg().getAcg().getIdACG())+"?msg="+message;
		    }
	  }
  }

