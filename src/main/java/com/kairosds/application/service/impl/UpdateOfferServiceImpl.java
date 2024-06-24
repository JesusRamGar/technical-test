package com.kairosds.application.service.impl;

import com.kairosds.domain.model.Offer;
import com.kairosds.domain.repository.OfferRepository;
import com.kairosds.domain.service.UpdateOfferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UpdateOfferServiceImpl implements UpdateOfferService {

  private final OfferRepository offerRepository;

  @Override
  public Offer updateOffer(Offer offer) {
    return offerRepository.saveOffer(offer);
  }
}
