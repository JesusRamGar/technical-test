package com.kairosds.api_rest.mapper;

import com.kairosds.domain.model.Offer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.OfferRequestDTO;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
public interface OfferRequestMapper {

  @Mapping(
      target = "size",
      expression = "java(processProductPartNumber(offerRequestDTO.getProductPartNumber(), 0, 2))")
  @Mapping(
      target = "model",
      expression = "java(processProductPartNumber(offerRequestDTO.getProductPartNumber(), 2, 6))")
  @Mapping(
      target = "quality",
      expression = "java(processProductPartNumber(offerRequestDTO.getProductPartNumber(), 6, 9))")
  @Mapping(target = "startDate", source = "startDate", qualifiedByName = "asTimestamp")
  @Mapping(target = "endDate", source = "endDate", qualifiedByName = "asTimestamp")
  @Mapping(target = "offerId", ignore = true)
  Offer toOffer(OfferRequestDTO offerRequestDTO);

  @Named("processProductPartNumber")
  default String processProductPartNumber(String productPartNumber, int beginIndex, int endIndex) {
    if (productPartNumber != null && productPartNumber.length() != 9) {
      throw new IllegalArgumentException("The productPartNumber field must be 9 characters long");
    }
    return StringUtils.hasText(productPartNumber)
        ? productPartNumber.substring(beginIndex, endIndex)
        : null;
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
