package com.sena.solution.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ParroquiaAcgPK implements Serializable {

  @Column(name = "id_parroquia", insertable=false, updatable=false)
  private Long idParroquia;

  @Column(name = "id_acg",insertable=false, updatable=false)
  private Long idACG;

  
  public ParroquiaAcgPK() {
  }

  public ParroquiaAcgPK(Long idParroquia, Long idACG) {
    this.idParroquia = idParroquia;
    this.idACG = idACG;
  }

  public Long getIdParroquia() {
    return idParroquia;
  }

  public void setIdParroquia(Long idParroquia) {
    this.idParroquia = idParroquia;
  }

  public Long getIdACG() {
    return idACG;
  }

  public void setIdACG(Long idACG) {
    this.idACG = idACG;
  }

  @Override
  public int hashCode() {
    
    return super.hashCode();
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return idParroquia.toString() + idACG.toString();
  }
  
  
}
