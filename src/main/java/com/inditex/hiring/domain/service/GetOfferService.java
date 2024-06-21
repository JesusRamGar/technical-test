package com.inditex.hiring.domain.service;

import com.inditex.hiring.domain.model.Offer;
import java.util.List;

public interface GetOfferService {

  Offer getOfferById(Long id);

  List<Offer> getAllOffers();
}
