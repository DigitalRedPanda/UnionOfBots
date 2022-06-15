package com.digiunion.unifiedbots.twitch.services.info;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public sealed interface InfoService permits Info {

  Dotenv env = Dotenv.configure()
      .directory("src\\main\\resources\\bot\\twitch")
      .filename("twitch.env")
      .load();

  String token = env.get("TOKEN");

  String clientId = env.get("CLIENT_ID");

  String clientSecret = env.get("CLIENT_SECRET");

  String redirectUrl = env.get("REDIRECT_URL");

  String owner = env.get("OWNER");

}
