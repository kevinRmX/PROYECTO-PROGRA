package com.example.webflux.api.controller;

import com.example.webflux.api.model.Productos;
import com.example.webflux.api.model.Usuario;
import com.example.webflux.api.repositorio.productoRepository;
import com.example.webflux.api.utils.InfoProduc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductoController {
  @Autowired
  private productoRepository repository;

  @GetMapping(path = "/producto/info", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Productos> feed() {
    return this.repository.findAll();
  }



}
