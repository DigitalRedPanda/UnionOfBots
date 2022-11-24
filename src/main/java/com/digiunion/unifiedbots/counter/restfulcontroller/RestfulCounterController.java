package com.digiunion.unifiedbots.counter.restfulcontroller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digiunion.unifiedbots.counter.service.CounterService;

@RestController
@RequestMapping("/counter")
@Async
public class RestfulCounterController {

  @Autowired
  CounterService counter;

  @GetMapping("/count")
  public CompletableFuture<Integer> count(@RequestParam(name = "channelName") String channelName)
      throws InterruptedException, ExecutionException {
    return CompletableFuture.supplyAsync(() -> channelName)
        .thenApply(counter::updateCounter);
  }

  @PostMapping("/reset")
  public CompletableFuture<Void> reset(@RequestParam(name = "channelName") String channelName)
      throws InterruptedException, ExecutionException {
    return CompletableFuture.supplyAsync(() -> channelName).thenAccept(counter::resetCounter);
  }
}
