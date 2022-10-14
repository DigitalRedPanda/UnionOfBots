package com.digiunion.unifiedbots.twitch.services.date.converter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

 @Override
 public Date convertToDatabaseColumn(LocalDate localDateToConvert) {
  return Optional.ofNullable(localDateToConvert).map(Date::valueOf).get();
 }

 @Override
 public LocalDate convertToEntityAttribute(Date sqlDateToConvert) {
  return Optional.ofNullable(sqlDateToConvert).map(Date::toLocalDate).get();
 }

}