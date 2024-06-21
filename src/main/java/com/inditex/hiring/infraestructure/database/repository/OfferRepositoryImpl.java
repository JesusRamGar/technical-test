package com.inditex.hiring.infraestructure.database.repository;

import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.infraestructure.database.mapper.OfferEntityMapper;
import com.inditex.hiring.infraestructure.database.repository.jpa.OfferRepositoryJpa;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@AllArgsConstructor
@Repository
public class OfferRepositoryImpl implements OfferRepository {

  private final OfferRepositoryJpa offerRepositoryJpa;

  private final OfferEntityMapper offerEntityMapper;

  public Offer createOffer(Offer offer) {
    return offerEntityMapper.toOffer(
        offerRepositoryJpa.save(offerEntityMapper.toOfferEntity(offer)));
  }

  public Optional<Offer> getOfferById(Long id) {
    return offerRepositoryJpa.findById(id).map(offerEntityMapper::toOffer);
  }

  public List<Offer> getAllOffers() {
    return offerRepositoryJpa.findAll().stream()
        .map(offerEntityMapper::toOffer)
        .collect(Collectors.toList());
  }

  public void deleteOfferById(Long id) {
    offerRepositoryJpa.deleteById(id);
  }

  public void deleteAllOffers() {
    offerRepositoryJpa.deleteAllInBatch();
  }
}
