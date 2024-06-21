package com.inditex.hiring.application.service.impl;

import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.service.DeleteOfferService;
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
