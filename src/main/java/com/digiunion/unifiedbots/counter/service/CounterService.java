package com.digiunion.unifiedbots.counter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digiunion.unifiedbots.twitch.repositories.channel.ChannelRepository;

@Service
public class CounterService {

 @Autowired
 ChannelRepository repository;

 public int getCounter(String ChannelName) {
  return repository.findById(ChannelName).get().getCounter();
 }

 public int updateCounter(String channelName) {
  var channel = repository.findById(channelName).get();
  channel.setCounter(channel.getCounter() + 1);
  return repository.save(channel).getCounter();

 }

 public void resetCounter(String channelName) {
  var channel = repository.findById(channelName).get();
  channel.setCounter(0);
  repository.save(channel);
 }

}
