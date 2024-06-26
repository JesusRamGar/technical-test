package com.kairosds.api_rest.mapper;

import com.kairosds.domain.model.Offer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.OfferDTO;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
public interface OfferMapper {

  @Mapping(target = "productPartNumber", expression = "java(generateProductPartNumber(offer))")
  @Mapping(target = "startDate", source = "startDate", qualifiedByName = "asString")
  @Mapping(target = "endDate", source = "endDate", qualifiedByName = "asString")
  OfferDTO toOfferDTO(Offer offer);

  @Named("generateProductPartNumber")
  default String generateProductPartNumber(Offer offer) {
    StringBuilder sb = new StringBuilder();

    if (StringUtils.hasText(offer.getSize())) {
      sb.append(offer.getSize());
    }

    if (StringUtils.hasText(offer.getModel())) {
      sb.append(offer.getModel());
    }

    if (StringUtils.hasText(offer.getQuality())) {
      sb.append(offer.getQuality());
    }

    return sb.toString();
  }

  @Named("asString")
  default String asString(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    LocalDateTime localDateTime = timestamp.toLocalDateTime();
    ZoneOffset offsetUTC = ZoneOffset.UTC;
    String dateString =
        localDateTime.atOffset(offsetUTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    return dateString.replace(":", ".");
  }
}
