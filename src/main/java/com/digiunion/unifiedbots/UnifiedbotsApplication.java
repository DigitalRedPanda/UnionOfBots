package com.digiunion.unifiedbots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.digiunion.unifiedbots.twitch.Bot;

@SpringBootApplication
@ComponentScan({ "com.digiunion.unifiedbots.twitch.services.info", "com.digiunion.unifiedbots.twitch",
		"com.digiunion.unifiedbots.twitch.listeners.commands" })
public class UnifiedbotsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UnifiedbotsApplication.class, args);
		var bot = context.getBean(Bot.class);
		bot.run();
	}

}
