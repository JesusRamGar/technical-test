package com.kairosds.application.service.impl;

import com.kairosds.domain.repository.OfferRepository;
import com.kairosds.domain.service.DeleteOfferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class DeleteOfferServiceImpl implements DeleteOfferService {

  private final OfferRepository offerRepository;

  @Override
  public void deleteOfferById(Long id) {
    offerRepository.deleteOfferById(id);
  }

  @Override
  public void deleteAllOffers() {
    offerRepository.deleteAllOffers();
  }
}
