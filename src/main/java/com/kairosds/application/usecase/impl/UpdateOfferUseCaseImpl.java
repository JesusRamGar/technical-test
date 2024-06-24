package com.kairosds.application.usecase.impl;

import com.kairosds.domain.model.Offer;
import com.kairosds.domain.service.GetOfferService;
import com.kairosds.domain.service.UpdateOfferService;
import com.kairosds.domain.usecase.UpdateOfferUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class UpdateOfferUseCaseImpl implements UpdateOfferUseCase {

  private final GetOfferService getOfferService;

  private final UpdateOfferService updateOfferService;

  @Override
  public Offer updateOffer(Long id, Offer offer) {
    getOfferService.getOfferById(id);

    offer.setOfferId(id);
    return updateOfferService.updateOffer(offer);
  }
}
