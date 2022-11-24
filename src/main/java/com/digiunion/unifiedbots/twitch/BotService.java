package com.digiunion.unifiedbots.twitch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digiunion.unifiedbots.twitch.entity.channel.Channel;

@Service
public sealed interface BotService permits TwitchBot {

 void initialize();

 void load(List<Channel> list);

 void terminate();

 boolean joinChannel(String channel);

 boolean leaveChannel(String channel);
}
