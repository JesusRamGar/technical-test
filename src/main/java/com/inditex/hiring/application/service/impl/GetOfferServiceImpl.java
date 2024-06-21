package com.inditex.hiring.application.service.impl;

import com.inditex.hiring.domain.exception.OfferNotFoundException;
import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.service.GetOfferService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class GetOfferServiceImpl implements GetOfferService {

  private final OfferRepository offerRepository;

  @Override
  public Offer getOfferById(Long id) {
    return offerRepository
        .getOfferById(id)
        .orElseThrow(
            () -> new OfferNotFoundException(String.format("Offer with id %s not found", id)));
  }

  @Override
  public List<Offer> getAllOffers() {
    return offerRepository.getAllOffers();
  }
}
