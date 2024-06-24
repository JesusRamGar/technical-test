package com.kairosds.domain.usecase;

import com.kairosds.domain.model.Offer;

public interface UpdateOfferUseCase {

  Offer updateOffer(Long id, Offer offer);
}
