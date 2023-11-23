package com.sena.solution.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class ParroquiaAcg {
  
  @EmbeddedId
  @Column(name = "id_main")
  private ParroquiaAcgPK id;

  @ManyToOne
  @MapsId("idParroquia")
  @JoinColumn(name = "id_parroquia")
  private Parroquia parroquia;

  @ManyToOne
  @MapsId("idACG")
  @JoinColumn(name = "id_acg")
  private ArchivoCategoriaGeneral acg;

  public ParroquiaAcg() {
  }

  public ParroquiaAcg(ParroquiaAcgPK id,Parroquia parroquia, ArchivoCategoriaGeneral acg) {
    this.parroquia = parroquia;
    this.acg = acg;
    this.id = id;
  }

  public ParroquiaAcgPK getId() {
    return id;
  }

  public void setId(ParroquiaAcgPK id) {
    this.id = id;
  }

  public Parroquia getParroquia() {
    return parroquia;
  }

  public void setParroquia(Parroquia parroquia) {
    this.parroquia = parroquia;
  }

  public ArchivoCategoriaGeneral getAcg() {
    return acg;
  }

  public void setAcg(ArchivoCategoriaGeneral acg) {
    this.acg = acg;
  }
}
