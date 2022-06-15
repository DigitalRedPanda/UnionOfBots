package com.digiunion.unifiedbots.twitch;

import org.springframework.stereotype.Service;

@Service
public sealed interface BotService permits Bot {

 void initialize();

 boolean joinChannel(String channel);

 boolean leaveChannel(String channel);
}
