package com.inditex.hiring.api_rest.mapper;

import com.inditex.hiring.domain.model.Offer;
import org.mapstruct.Mapper;
import org.openapitools.model.OfferDTO;

@Mapper(componentModel = "spring")
public interface OfferMapper {

  OfferDTO toOfferDTO(Offer offer);

  Offer toOffer(OfferDTO offerDTO);
}
