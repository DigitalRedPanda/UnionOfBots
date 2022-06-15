package com.digiunion.unifiedbots.twitch.services.info;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public final class Info implements InfoService {
 @Getter
 Information info = new Information(token, clientId, clientSecret, redirectUrl, owner);

 private record Information(String token, String clientId, String clientSecret, String redirectUrl, String owner) {
 };

}
