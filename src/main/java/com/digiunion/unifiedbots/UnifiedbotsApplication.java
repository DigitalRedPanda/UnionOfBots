package com.digiunion.unifiedbots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.digiunion.unifiedbots.discord.bot.DiscordBot;
import com.digiunion.unifiedbots.twitch.TwitchBot;

import lombok.Getter;

@SpringBootApplication
@ComponentScan({ "" })
public class UnifiedbotsApplication {

	@Getter
	static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(UnifiedbotsApplication.class, args);
		var twitchBot = context.getBean(TwitchBot.class);
		var discordBot = context.getBean(DiscordBot.class);
		twitchBot.run();
		discordBot.run();
		// Optional.ofNullable(sqlDateToConvert).map(Date::toLocalDate);
	}

}
