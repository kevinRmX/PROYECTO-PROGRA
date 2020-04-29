package com.example.webflux.api.model;

public class Productos {
  private String nombreProducto;
  private String Descripcion;
  private String tiempo;

  public Productos(String randomNombre, String randomDescripcion, String getproductoTimeStamp) {
    this.nombreProducto = randomNombre;
    this.Descripcion = randomDescripcion;
    this.tiempo = getproductoTimeStamp;
  }

  public String getNombreProducto() {
    return nombreProducto;
  }

  public void setNombreProducto(String nombreProducto) {
    this.nombreProducto = nombreProducto;
  }

  public String getDescripcion() {
    return Descripcion;
  }

  public void setDescripcion(String descripcion) {
    Descripcion = descripcion;
  }

  public String getTiempo() {
    return tiempo;
  }

  public void setTiempo(String tiempo) {
    this.tiempo = tiempo;
  }
}

