package com.kairosds.application.service.impl;

import com.kairosds.domain.model.Offer;
import com.kairosds.domain.repository.OfferRepository;
import com.kairosds.domain.service.CreateOfferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CreateOfferServiceImpl implements CreateOfferService {

  private final OfferRepository offerRepository;

  @Override
  public Offer createOffer(Offer offer) {
    return offerRepository.createOffer(offer);
  }
}
