package com.digiunion.unifiedbots.twitch;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.digiunion.unifiedbots.twitch.entity.channel.Channel;
import com.digiunion.unifiedbots.twitch.listeners.commands.CommandConsumer;
import com.digiunion.unifiedbots.twitch.listeners.message.MessageService;
import com.digiunion.unifiedbots.twitch.repositories.channel.ChannelRepository;
import com.digiunion.unifiedbots.twitch.services.info.InfoService;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public final class TwitchBot implements BotService {
  @Autowired
  @Qualifier("info")
  static InfoService info;

  @Autowired
  @Qualifier("commandListener")
  CommandConsumer commandEvent;

  @Autowired
  @Qualifier("messageListener")
  MessageService messageEvent;

  @Autowired
  ChannelRepository channelRepository;

  @Getter
  static OAuth2Credential credential = new OAuth2Credential("twitch", info.token);

  @Getter
  static TwitchClient client = TwitchClientBuilder.builder()
      .withEnableChat(true)
      .withDefaultAuthToken(credential)
      .withChatAccount(credential)
      .withCommandTrigger("!")
      // .withScheduledThreadPoolExecutor(new
      // ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors()))
      .withEnableHelix(true)
      .withEnableGraphQL(true)
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

      if (channelRepository.existsById(channel)) {

        client.getChat().joinChannel(channel);

        log.info("{} has been loaded", channel);

        return true;

      }

      client.getChat().sendMessage(channel,
          "Hello %s and chat, I came here because my coder told me that عندكم مضبي وعلشانكم اهل الديرة قصمان, راح تضبطوني OpieOP "
              .formatted(channel));

      log.info("Joined {}'s channel", channel);

      channelRepository.save(new Channel(channel));

      return true;

    }

    client.getChat().sendMessage("Digital_Red_Panda",
        "خلصت العزيمة وش قاعد تقول, القصمان ولو".formatted(channel));

    log.error("Failed to join {}'s channel; channel is already joined", channel);

    return false;

  }

  @Override
  public boolean leaveChannel(String channel) {

    log.info("Leaving {}'s channel", channel);

    if (!client.getChat().isChannelJoined(channel)) {

      client.getChat().sendMessage("Digital_Red_Panda",
          "خلصت العزيمة وش قاعد تقول, القصمان ولو");

      log.error("Failed to leave {}'s channel; channel isn't joined", channel);

      return false;

    }

    client.getChat().sendMessage(channel,
        "علشانكم اكلتو بدوني بطلع tc %s and chat Sadge".formatted(channel));

    client.getChat().leaveChannel(channel);

    channelRepository.deleteById(channel);

    log.info("Left and removed {}'s channel", channel);

    return true;

  }

  public void run() {

    CompletableFuture.runAsync(() -> initialize()).thenRun(() -> load(channelRepository.findAll())).join();

    CompletableFuture.allOf(
        CompletableFuture
            .runAsync(() -> client.getChat().getEventManager().onEvent(ChannelMessageEvent.class, commandEvent)),
        CompletableFuture
            .runAsync(() -> client
                .getChat().getEventManager().onEvent(ChannelMessageEvent.class, messageEvent)))
        .join();

    /*
     * initialize();
     * 
     * load(channelRepository.findAll());
     * 
     * client.getChat().getEventManager().onEvent(ChannelMessageEvent.class,
     * commandEvent);
     * 
     * client.getChat().getEventManager().onEvent(ChannelMessageEvent.class,
     * messageEvent);
     */

  }

  @Override
  public void load(List<Channel> channels) {

    if (!channels.isEmpty()) {
      log.error("Could not initiate loading channels; the list is empty");
      return;
    }

    channels.parallelStream().forEach(channel -> joinChannel(channel.getChannelName()));
    log.info("Loaded all of stored channels");

  }

  @Override
  public void terminate() {
    client.close();
  }

}
