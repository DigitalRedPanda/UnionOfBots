package com.digiunion.unifiedbots.twitch.services.info;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public final class Info implements InfoService {

 @Getter
 private Information info = new Information(token, clientId, clientSecret, redirectUrl, owner);

 private record Information(String token, String clientId, String clientSecret, String redirectUrl, String owner) {
 };

}
