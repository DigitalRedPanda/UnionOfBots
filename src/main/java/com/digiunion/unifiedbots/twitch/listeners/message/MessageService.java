package com.digiunion.unifiedbots.twitch.listeners.message;

import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

@Service
public sealed interface MessageService extends Consumer<ChannelMessageEvent>permits MessageListener {
}