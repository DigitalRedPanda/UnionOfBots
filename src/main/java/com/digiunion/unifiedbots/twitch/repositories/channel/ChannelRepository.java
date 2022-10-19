package com.digiunion.unifiedbots.twitch.repositories.channel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digiunion.unifiedbots.twitch.entity.channel.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, String> {

 @Query(value = "SELECT name FROM Channel", nativeQuery = true)
 List<String> getAllChannelIds();

};
