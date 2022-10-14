package com.digiunion.unifiedbots.twitch.entity.channel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Channel {

 @Id
 @SequenceGenerator(name = "id", sequenceName = "id", allocationSize = 1)
 @GeneratedValue(strategy = GenerationType.SEQUENCE)
 @Column(name = "id")
 private Long channelId;

 @Column(name = "name", columnDefinition = "VARCHAR(40)", unique = true)
 @NonNull
 private String channelName;

 // @Column(name = "absent", nullable = false, columnDefinition = "INTEGER 0
 // CHECK(absent IN (0,1))")
 // private boolean absent;
}
