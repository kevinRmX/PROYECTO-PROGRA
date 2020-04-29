package com.example.webflux.api.utils;

import com.example.webflux.api.model.Productos;
import com.example.webflux.api.model.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InfoProduc {

  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
  private static final Random RANDOM = new Random(System.currentTimeMillis());

  private static final List<String>  PRODUCTO_NOMBRE =
    Arrays.asList(
      "cocacola", "maiz", "frijol","arroz", "lechuga");

  private static final List<String> PRODUCTO_DESCRIPCION =
    Arrays.asList(
      "Estan en oferta",
      "No estan disponibles",
      "Nesecito varias porciones");

  public static String randomNombre() {
    return PRODUCTO_NOMBRE.get(RANDOM.nextInt(PRODUCTO_NOMBRE.size()));
  }

  public static String randomDescripcion() {
    return PRODUCTO_DESCRIPCION.get(RANDOM.nextInt(PRODUCTO_DESCRIPCION.size()));
  }

  public static String getproductoTimeStamp() {
    return dtf.format(LocalDateTime.now());
  }
}
