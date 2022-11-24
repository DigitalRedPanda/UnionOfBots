package com.digiunion.unifiedbots.discord.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digiunion.unifiedbots.discord.bot.info.InfoService;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.presence.ClientPresence;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public final class BotService implements DiscordBot {

  @Autowired
  private InfoService info;

  @Getter
  private DiscordClient client;

  @Override
  public void initialize() {
    client = DiscordClient.create(info.getToken());
    log.info("Initiating the bot");
    client.gateway().setInitialPresence(presence -> ClientPresence.doNotDisturb())
        .withGateway(client -> client.on(ReadyEvent.class)
            .doOnNext(ready -> log.info("Successfully logged in as {}", ready.getSelf().getTag())))
        .block();
  }

  @Override
  public void run() {
    initialize();
    client.withGateway(client -> client.on(MessageCreateEvent.class, event -> Mono.fromRunnable(() -> {
      var user = event.getMember().get();
      var message = event.getMessage().getContent();

    })));
  }

  @Override
  public void stop() {

  }

  @Override
  public boolean isRunning() {
    return false;
  }

}