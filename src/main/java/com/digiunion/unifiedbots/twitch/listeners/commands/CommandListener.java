package com.digiunion.unifiedbots.twitch.listeners.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.digiunion.unifiedbots.twitch.BotService;
import com.digiunion.unifiedbots.twitch.TwitchBot;
import com.digiunion.unifiedbots.twitch.entity.channel.Channel;
import com.digiunion.unifiedbots.twitch.repositories.channel.ChannelRepository;
import com.digiunion.unifiedbots.twitch.services.date.counter.DateCounter;
import com.digiunion.unifiedbots.twitch.services.info.InfoService;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Async
public non-sealed class CommandListener implements CommandConsumer {

  @Autowired
  @Qualifier("twitchBot")
  BotService botClient;

  @Autowired
  @Qualifier("info")
  InfoService information;

  @Autowired
  ChannelRepository channelRepository;

  DateCounter counter;

  Optional<CompletableFuture<Void>> completableFuture = Optional.empty();

  @Override
  public void accept(ChannelMessageEvent event) {

    var user = event.getUser().getName();

    var channel = event.getChannel().getName();

    var commandContent = Arrays.asList(event.getMessage().split(" "));

    var command = commandContent.get(0).toLowerCase().replaceFirst("!", "");

    var client = TwitchBot.getClient();

    if (commandContent.get(0).startsWith("!") && user.equals("digital_red_panda")) {

      log.info("{} used {} which contains {} on {}", user, command, commandContent,
          channel);

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

          channelRepository.save(null);

          return;

        }

        client.getChat().sendMessage(channel,
            "moerQatsim 💢 ما علموك اسلافك كيف تفرق بين الكورة الطايرة والكليجة؟ %s".formatted(user));

      }
      List<Integer> numberList = List.of(2, 7, 3);
      var someList = numberList.stream().filter(number -> number % 2 != 0).toList();

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