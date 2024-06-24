package com.kairosds.api_rest.mapper;

import com.kairosds.domain.model.Offer;
import org.mapstruct.Mapper;
import org.openapitools.model.OfferDTO;

@Mapper(componentModel = "spring")
public interface OfferMapper {

  OfferDTO toOfferDTO(Offer offer);

  Offer toOffer(OfferDTO offerDTO);
}
