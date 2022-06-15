package com.digiunion.unifiedbots.twitch.listeners.commands;

import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.github.twitch4j.chat.events.CommandEvent;

@Service
public sealed interface CommandConsumer extends Consumer<CommandEvent>permits CommandListener {

}