package com.example.webflux.api.repositorio;

import com.example.webflux.api.model.Productos;
import reactor.core.publisher.Flux;

public interface productoRepository {
  Flux<Productos> findAll();
}
