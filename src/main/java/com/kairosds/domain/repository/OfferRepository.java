package com.kairosds.domain.repository;

import com.kairosds.domain.model.Offer;
import java.util.List;
import java.util.Optional;

public interface OfferRepository {

  Offer saveOffer(Offer offer);

  Optional<Offer> getOfferById(Long id);

  List<Offer> getAllOffers();

  void deleteOfferById(Long id);

  void deleteAllOffers();
}
