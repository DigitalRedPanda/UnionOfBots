package com.digiunion.unifiedbots.twitch.listeners.commands;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.digiunion.unifiedbots.twitch.BotService;
import com.digiunion.unifiedbots.twitch.TwitchBot;
import com.digiunion.unifiedbots.twitch.repositories.channel.ChannelRepository;
import com.digiunion.unifiedbots.twitch.services.info.InfoService;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public non-sealed class CommandListener implements CommandConsumer {

  @Autowired
  @Qualifier("twitchBot")
  BotService botClient;

  @Autowired
  @Qualifier("info")
  InfoService information;

  @Autowired
  ChannelRepository channelRepository;

  // DateCounter counter;

  @Override
  public void accept(ChannelMessageEvent event) {

    var commandContent = Arrays.asList(event.getMessage().split(" "));

    var user = event.getUser().getName();

    if (commandContent.get(0).charAt(0) == '!'
        && user.equals("digital_red_panda")) {

      var channel = event.getChannel().getName();

      var command = commandContent.get(0).toLowerCase().replaceFirst("!", "");

      var client = TwitchBot.getClient();

      log.info("[{}] {}: {} {}", channel, user, command, commandContent);

      if (command.equals("join")) {

        if (commandContent.size() == 2) {

          botClient.joinChannel(commandContent.get(1));

          return;

        }

        client.getChat().sendMessage(channel, "moerQatsim 💢 ما علموك اسلافك كيف تفرق بين الكورة الطايرة والكليجة؟ %s"
            .formatted(user));

      }

      if (command.equals("leave")) {

        if (commandContent.size() == 2) {

          botClient.leaveChannel(commandContent.get(1));

          return;

        }

        client.getChat().sendMessage(channel,
            "moerQatsim 💢 ما علموك اسلافك كيف تفرق بين الكورة الطايرة والكليجة؟ %s".formatted(user));

      }

      if (command.equals("channels"))

        event.getTwitchChat().sendMessage(channel,
            "Joined channels are %s"
                .formatted(channelRepository.getAllChannelIds().toString().replaceAll("[\\[\\]]", "")));

      /*
       * if (command.equals("poll")) {
       * var prediction = client.getHelix()
       * .createPrediction(information.token,
       * Prediction.builder().broadcasterName(channel).broadcasterId(event.getChannel(
       * ).getId())
       * .predictionWindowSeconds(40).title("test").outcomes(List.of(
       * PredictionOutcome.builder().color(PredictionColor.BLUE).title("test1").build(
       * ),
       * PredictionOutcome.builder().color(PredictionColor.PINK).title("test2").build(
       * )))
       * .build())
       * .execute();
       * 
       * prediction.getPredictions().forEach(System.out::println);
       * }
       */
      /*
       * if (command.equals("updatecount")) {
       * ((DateCounterService) counter).getCounter();
       * log.info("Counter has been set to {} by {}", ((DateCounterService)
       * counter).getCounter(), user);
       * }
       * if (command.equals("count") && commandContent.size() == 2) {
       * if (commandContent.get(2).matches("[0-3]*[0-9]\\"))
       * try {
       * counter = counter.createDateCounter(LocalDate.of(year, month, dayOfMonth));
       * } catch (InterruptedException e) {
       * log.
       * error("Counter launching has failed due to an error as in the following:\n{}"
       * , e.toString());
       * }
       * }
       * 
       * if (command.equals("stopcounter")) {
       * if (completableFuture.isPresent()) {
       * completableFuture.get().complete(null);
       * log.info("Counter has been stopped by {}", user);
       * return;
       * }
       * event.reply(client.getChat(), "The counter hasn't been started");
       * log.warn("Failed to start a counter, counter isn't initiated");
       * }
       */
      // var poll =
      // Poll.builder().broadcasterName(channel).id(event.getChannel().getId()).durationSeconds(120)
      // .choices(List.of(PollChoice.builder().title("test1").build(),
      // PollChoice.builder().title("test2").build())).build();

    }
  }

}