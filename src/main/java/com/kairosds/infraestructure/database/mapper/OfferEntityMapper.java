package com.kairosds.infraestructure.database.mapper;

import com.kairosds.domain.model.Offer;
import com.kairosds.infraestructure.database.model.OfferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferEntityMapper {

  Offer toOffer(OfferEntity offerEntity);

  OfferEntity toOfferEntity(Offer offer);
}
