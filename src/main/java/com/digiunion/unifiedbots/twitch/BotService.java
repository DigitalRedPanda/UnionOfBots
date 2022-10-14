package com.digiunion.unifiedbots.twitch;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.digiunion.unifiedbots.twitch.entity.channel.Channel;

@Service
public sealed interface BotService permits TwitchBot {

 void initialize();

 void load(List<Channel> list);

 boolean joinChannel(String channel);

 boolean leaveChannel(String channel);
}
