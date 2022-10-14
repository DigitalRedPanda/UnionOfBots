package com.digiunion.unifiedbots.discord.bot;

import org.springframework.stereotype.Component;

@Component
public sealed interface DiscordBot permits BotService {

 void initialize();

 void run();

 boolean isRunning();

}
