package com.kairosds.infraestructure.database.mapper;

import com.kairosds.domain.model.Offer;
import com.kairosds.infraestructure.database.model.OfferEntity;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
public interface OfferEntityMapper {

  @Mapping(
      target = "productPartNumber",
      expression = "java(generateProductPartNumber(offerEntity))")
  @Mapping(target = "startDate", source = "startDate", qualifiedByName = "asString")
  @Mapping(target = "endDate", source = "endDate", qualifiedByName = "asString")
  Offer toOffer(OfferEntity offerEntity);

  @Mapping(
      target = "size",
      expression = "java(processProductPartNumber(offer.getProductPartNumber(), 0, 2))")
  @Mapping(
      target = "model",
      expression = "java(processProductPartNumber(offer.getProductPartNumber(), 2, 6))")
  @Mapping(
      target = "quality",
      expression = "java(processProductPartNumber(offer.getProductPartNumber(), 6, 9))")
  @Mapping(target = "startDate", source = "startDate", qualifiedByName = "asTimestamp")
  @Mapping(target = "endDate", source = "endDate", qualifiedByName = "asTimestamp")
  OfferEntity toOfferEntity(Offer offer);

  @Named("generateProductPartNumber")
  default String generateProductPartNumber(OfferEntity offerEntity) {
    StringBuilder sb = new StringBuilder();

    if (StringUtils.hasText(offerEntity.getSize())) {
      sb.append(offerEntity.getSize());
    }

    if (StringUtils.hasText(offerEntity.getModel())) {
      sb.append(offerEntity.getModel());
    }

    if (StringUtils.hasText(offerEntity.getQuality())) {
      sb.append(offerEntity.getQuality());
    }

    return sb.toString();
  }

  @Named("processProductPartNumber")
  default String processProductPartNumber(String productPartNumber, int beginIndex, int endIndex) {
    if (productPartNumber != null && productPartNumber.length() != 9) {
      throw new IllegalArgumentException("The productPartNumber field must be 9 characters long");
    }
    return StringUtils.hasText(productPartNumber)
        ? productPartNumber.substring(beginIndex, endIndex)
        : null;
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

  @Named("asTimestamp")
  default Timestamp asTimestamp(String dateString) {
    if (dateString == null) {
      return null;
    }
    LocalDateTime localDateTime =
        LocalDateTime.parse(dateString.replace(".", ":"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    return Timestamp.valueOf(localDateTime);
  }
}
