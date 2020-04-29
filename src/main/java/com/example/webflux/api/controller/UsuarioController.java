package com.example.webflux.api.controller;

import com.example.webflux.api.model.Usuario;
import org.springframework.http.HttpStatus;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/usuario")
public class UsuarioController {

  private static List<Usuario> listaUsuario = new ArrayList<>();

  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
  public static String getproductoTimeStamp() {
    return dtf.format(LocalDateTime.now());
  }

  private EmitterProcessor<Usuario> notificationProcessor;

  @PostConstruct
  private void createProcessor() {
    notificationProcessor = EmitterProcessor.<Usuario>create();
  }

  @RequestMapping(
    value = "/all",
    method = RequestMethod.GET,
    produces = "application/json")
  public List<Usuario> getAll() {
    return listaUsuario;
  }


  @RequestMapping(
    path = "/create",
    method = RequestMethod.POST)
  public ResponseEntity<?> create(
    @RequestBody Usuario entityParam) {
    entityParam.setTiempo(getproductoTimeStamp());
    listaUsuario.add(entityParam);



    System.out.println("Notificando nueva persona: " + entityParam.getPrimerNombre() +" "+ entityParam.getSegundoNombre() + getproductoTimeStamp() );
    notificationProcessor.onNext(entityParam);
    return new ResponseEntity<>(entityParam, HttpStatus.OK);
  }

  private Flux<ServerSentEvent<Usuario>> getNotificationHeartbeat() {
    return Flux.interval(Duration.ofSeconds(10))
      .map(i -> {
        System.out.println(String.format("enviando latidos [%s] ...", i.toString()));
        return ServerSentEvent.<Usuario>builder()
          .id(String.valueOf(i))
          .event("Latido_Resultalte")
          .data(null)
          .build();
      });
  }
@GetMapping(value = "/suscrito")
  private Flux<ServerSentEvent<Usuario>> getPersonaSSE() {
    // notification processor retorna un FLUX en el cual podemos estar "suscritos" cuando este tenga otro valor ...
    return notificationProcessor
      .log().map(
        (persona) -> {
          System.out.println("Enviando Persona:" + persona.getPrimerNombre());
          return ServerSentEvent.<Usuario>builder()
            .id(UUID.randomUUID().toString())
            .event("persona-result")
            .data(persona)
            .build();
        }).concatWith(Flux.never());
  }
  @GetMapping(
    value = "/notificacion/datos"
  )
  public Flux<ServerSentEvent<Usuario>>
  getJobResultNotification() {

    return Flux.merge(
      getNotificationHeartbeat(),
      getPersonaSSE()
    );
  }

  @RequestMapping(
    path = "/update",
    method = RequestMethod.PUT)
  public ResponseEntity<?> update(
    @RequestBody Usuario entityParam) {
entityParam.setTiempo(getproductoTimeStamp());
listaUsuario.add(entityParam);

    System.out.println("El usuario "+ entityParam.getPrimerNombre()+ " " + entityParam.getSegundoNombre() + " a sido ACTUALIZADO  a las " + getproductoTimeStamp());
    notificationProcessor.onNext(entityParam);
    return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
  }
}
