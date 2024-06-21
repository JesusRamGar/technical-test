package com.inditex.hiring.application.service.impl;

import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.service.CreateOfferService;
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
