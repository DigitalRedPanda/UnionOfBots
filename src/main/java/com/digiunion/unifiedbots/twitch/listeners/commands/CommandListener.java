package com.digiunion.unifiedbots.twitch.listeners.commands;

import org.springframework.stereotype.Component;

import com.github.twitch4j.chat.events.CommandEvent;

@Component
public final class CommandListener implements CommandConsumer {

 @Override
 public void accept(CommandEvent event) {

  var user = event.getUser().getName();

  var channel = event.getSourceId();

  var command = event.getCommand();

  System.out.printf("%s used %s on %s", user, command, channel);

 }
}