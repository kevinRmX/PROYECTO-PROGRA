package com.example.webflux.api.repositorio;

import com.example.webflux.api.model.Productos;
import com.example.webflux.api.utils.InfoProduc;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
@Repository
public class ReactivoProductosRepository implements productoRepository {

    public Flux<Productos> findAll () {
    //simular nuestros datos
    return Flux.interval(Duration.ofSeconds(10))
      .onBackpressureDrop()
      .map(this::generacionMesages)
      .flatMapIterable(x -> x);
  }

  private List<Productos> generacionMesages(long interval) {

    Productos obj = new Productos(InfoProduc.randomNombre(), InfoProduc.randomDescripcion(), InfoProduc.getproductoTimeStamp());
    return Arrays.asList(obj);

  }

}
