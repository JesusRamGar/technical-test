package com.kairosds.api_rest.mapper;

import com.kairosds.domain.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.OfferRequestDTO;

@Mapper(componentModel = "spring")
public interface OfferRequestMapper {

  @Mapping(target = "offerId", ignore = true)
  Offer toOffer(OfferRequestDTO offerRequestDTO);
}
