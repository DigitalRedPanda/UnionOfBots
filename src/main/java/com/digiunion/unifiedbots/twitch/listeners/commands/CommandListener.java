package com.digiunion.unifiedbots.twitch.listeners.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.digiunion.unifiedbots.twitch.Bot;
import com.digiunion.unifiedbots.twitch.BotService;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public final class CommandListener implements CommandConsumer {

  @Autowired
  @Qualifier("bot")
  BotService botClient;

  @Override
  public void accept(ChannelMessageEvent event) {

    var user = event.getUser().getName();

    var channel = event.getChannel().getName();

    var commandContent = event.getMessage().split(" ");

    var command = commandContent[0].toLowerCase().replaceFirst("!", "");

    var client = ((Bot) botClient).getClient();

    if (commandContent[0].startsWith("!\\w+")) {

      log.info("{} used {} which contains {} on {}", user, command, commandContent, channel);

      if (command.equals("join")) {

        if (commandContent.length == 2) {

          botClient.joinChannel(commandContent[1]);

          return;

        }

        client.getChat().sendMessage(channel, "moerQassim 💢 ما علموك اسلافك كيف تفرق بين الكورة الطايرة والكليجة؟ %s"
            .formatted(user));

      }

      if (command.equals("leave")) {

        if (commandContent.length == 2) {

          botClient.leaveChannel(commandContent[1]);

          return;

        }

        client.getChat().sendMessage(channel,
            "moerQassimi 💢 ما علموك اسلافك كيف تفرق بين الكورة الطايرة والكليجة؟ %s".formatted(user));

      }

    }

  }

}