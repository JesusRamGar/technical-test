package com.inditex.hiring.domain.repository;

import com.inditex.hiring.domain.model.Offer;
import java.util.List;
import java.util.Optional;

public interface OfferRepository {

  Offer createOffer(Offer offer);

  Optional<Offer> getOfferById(Long id);

  List<Offer> getAllOffers();

  void deleteOfferById(Long id);

  void deleteAllOffers();
}
