package com.digiunion.unifiedbots.twitch;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.digiunion.unifiedbots.twitch.listeners.commands.CommandConsumer;
import com.digiunion.unifiedbots.twitch.services.info.InfoService;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope(value = "singleton")
@Component
public final class Bot implements BotService {

  @Autowired
  @Qualifier("info")
  InfoService info;

  @Autowired
  @Qualifier("commandListener")
  CommandConsumer commandEvent;

  OAuth2Credential credential = new OAuth2Credential("twitch", info.token);

  @Getter
  TwitchClient client = TwitchClientBuilder.builder()
      .withEnableChat(true)
      .withDefaultAuthToken(credential)
      .withChatAccount(credential)
      .withCommandTrigger("!")
      .withScheduledThreadPoolExecutor(new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors()))
      .build();

  @Override
  public void initialize() {

    log.info("initializing bot");

    if (client.getChat().isChannelJoined(info.owner)) {

      client.getChat().sendMessage(info.owner, "imGlitch OMK3NDY");

      log.info("Initialized bot as {} with the redirect URL being {}", info.owner, info.redirectUrl);

      return;

    }

    log.error("Failed to initialize bot, retrying");

    client.getChat().joinChannel(info.owner);

    initialize();

  }

  @Override
  public boolean joinChannel(String channel) {

    log.info("Joining {}'s channel", channel);

    if (!client.getChat().isChannelJoined(channel)) {

      client.getChat().sendMessage(channel,
          "Hello %s and chat, I came here because my coder told me that عندكم مضبي وعلشانكم اهل الديرة قصمان, راح تضبطوني OpieOP "
              .formatted(channel));

      client.getChat().joinChannel(channel);

      log.info("Joined {}'s channel", channel);
      return true;
    }

    client.getChat().sendMessage("Digital_Red_Panda",
        "خلصت العزيمة وش قاعد تقول, القصمان ولو".formatted(channel));

    log.warn("Failed to join {}'s channel, channel is already joined", channel);
    return false;

  }

  @Override
  public boolean leaveChannel(String channel) {

    log.info("Leaving {}'s channel", channel);

    if (client.getChat().isChannelJoined(channel)) {

      client.getChat().sendMessage(channel,
          "علشانكم اكلتو بدوني بطلع tc %s and chat Sadge".formatted(channel));

      client.getChat().leaveChannel(channel);

      log.info("Left {}'s channel", channel);

      return true;

    }

    client.getChat().sendMessage("Digital_Red_Panda",
        "خلصت العزيمة وش قاعد تقول, القصمان ولو");

    log.warn("Failed to leave {}'s channel, channel isn't joined", channel);

    return false;

  }

  public void run() {

    initialize();

    client.getChat().getEventManager().onEvent(ChannelMessageEvent.class, commandEvent);

  }

}
