package io.sse.springbootDemo.controller;

import io.sse.springbootDemo.service.SseService;
import java.time.Duration;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/events")
public class SseController {

  @Autowired
  private SseService sseService;

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping(path = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<String>> subscribe() {

    return sseService.getRandomData()
            .map(event->
                ServerSentEvent.<String> builder() /**create ServerSentEvent as supplier of string data*/
                    .id(String.valueOf(sseService.getEventId()))
                    .data(String.valueOf(event))
                    .event("test-event-1")
                    .comment("test-comment-1")
                    .retry(Duration.ofMillis(5000))
                    .build());
    }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping(path = "/health")
  public ResponseEntity<java.util.Date> ping() {
    return ResponseEntity.ok(new Date());
  }

}