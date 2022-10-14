package com.digiunion.unifiedbots.twitch.repositories.channel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.digiunion.unifiedbots.twitch.entity.channel.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

 @Query(value = "SELECT new com.digiunion.unifiedbots.twitch.entity.channel.Channel(channel.name,channel.id) FROM Channel WHERE name = :name", nativeQuery = true)
 Optional<Channel> findChannelByName(@NonNull @Param("name") String channelName);

 @Query(value = "SELECT pk FROM Channel WHERE name = :name", nativeQuery = true)
 long getChannelIdByName(@NonNull @Param("name") String name);

 @Query("DELETE FROM Channel WHERE name = :name")
 void deleteChannelByName(@NonNull @Param("name") String channelName);

};
