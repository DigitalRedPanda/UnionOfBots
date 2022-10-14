package com.digiunion.unifiedbots.discord.bot.info;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

@Service
public class InfoService {

  private Dotenv env = Dotenv.configure().directory("src/main/resources/bot/discord/")
      .filename("discord.env")
      .load();

  @Getter
  private String token = env.get("TOKEN");

}