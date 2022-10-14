package com.digiunion.unifiedbots.twitch.services.date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.digiunion.unifiedbots.twitch.entity.channel.Channel;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@Scope("prototype")
@Entity(name = "Absence_Period")
@RequiredArgsConstructor
public class AbsencePeriod {

 @Id
 @GeneratedValue(strategy = SEQUENCE)
 private Long id;

 @Column(name = "start_date", columnDefinition = "STRING", nullable = false)
 @NonNull
 private LocalDate startDate;

 @Column(name = "current_date", columnDefinition = "STRING", nullable = false)
 @NonNull
 private LocalDate currentDate;

 @OneToOne(fetch = EAGER)
 @JoinColumn(name = "id")
 private Channel channel;

}