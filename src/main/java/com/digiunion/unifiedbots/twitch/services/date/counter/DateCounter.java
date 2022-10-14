package com.digiunion.unifiedbots.twitch.services.date.counter;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public sealed interface DateCounter permits DateCounterService {

 void count() throws InterruptedException;

 public default DateCounter create(LocalDate startDate, String Channel) {
  return new DateCounterService();
 }

}
