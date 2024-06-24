package com.kairosds.domain.service;

import com.kairosds.domain.model.Offer;

import java.util.List;

public interface GetOfferService {

  Offer getOfferById(Long id);

  List<Offer> getAllOffers();
}
