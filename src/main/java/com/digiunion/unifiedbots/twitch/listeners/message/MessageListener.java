package com.digiunion.unifiedbots.twitch.listeners.message;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Async
public non-sealed class MessageListener implements MessageService {

  @Override
  public void accept(ChannelMessageEvent event) {

    if (!(event.getMessage().charAt(0) == '!')) {

      String channel = event.getChannel().getName();

      String user = event.getUser().getName();

      String message = event.getMessage();

      log.info("[{}]: {}: {}", channel, user, message);

      if (message.matches("Wanna become famous\\? Buy \\w+, \\w+ and \\w+ on \\w+\\s?\\.com")) {

        event.ban(user,
            "Do You want %s to become your mom? She/He's a queen, بس انت تزاعق كثير لدرجة كل البوتات الثانية كرهوك"
                .formatted(channel));

        log.warn("A spamming bot called {} has been banned on {}", user, channel);

      }

    }

  }

}
