package com.digiunion.unifiedbots.twitch.entity.channel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Channel {

 @Id
 @Column(name = "name", columnDefinition = "VARCHAR(40)")
 @NonNull
 private String channelName;

 @Column(columnDefinition = "counter INTEGER DEFAULT 0")
 private int counter;

 // @Column(name = "absent", nullable = false, columnDefinition = "INTEGER 0
 // CHECK(absent IN (0,1))")
 // private boolean absent;
}
