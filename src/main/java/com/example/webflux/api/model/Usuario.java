package com.example.webflux.api.model;



public class Usuario {

  private long id;
  private String primerNombre;
  private  String segundoNombre;
  private String correo;
  private String tiempo;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPrimerNombre() {
    return primerNombre;
  }

  public void setPrimerNombre(String primerNombre) {
    this.primerNombre = primerNombre;
  }

  public String getSegundoNombre() {
    return segundoNombre;
  }

  public void setSegundoNombre(String segundoNombre) {
    this.segundoNombre = segundoNombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTiempo() {
    return tiempo;
  }

  public void setTiempo(String tiempo) {
    this.tiempo = tiempo;
  }
}
