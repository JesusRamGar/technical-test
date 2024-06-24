package com.kairosds.infraestructure.database.repository;

import com.kairosds.domain.model.Offer;
import com.kairosds.domain.repository.OfferRepository;
import com.kairosds.infraestructure.database.mapper.OfferEntityMapper;
import com.kairosds.infraestructure.database.repository.jpa.OfferRepositoryJpa;
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
