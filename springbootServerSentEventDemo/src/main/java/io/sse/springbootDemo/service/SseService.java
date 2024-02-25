package io.sse.springbootDemo.service;

import static reactor.core.publisher.Flux.*;

import java.util.Date;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Log4j2
public class SseService {

     /**generate random UUID  as event data*/
    public Flux<String> getRandomData(){

      log.info("*****getRandomData starts:");

      Flux<String> flux = Flux.just("data : "+UUID.randomUUID().toString());

      return flux;
    }

    /**generate milli-second  as event id*/
    public Long getEventId(){
      log.info("*****getEventId starts:");
      return new Date().getTime();
    }
}