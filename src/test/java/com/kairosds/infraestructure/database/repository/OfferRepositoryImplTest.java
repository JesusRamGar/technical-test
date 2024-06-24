package com.kairosds.infraestructure.database.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.kairosds.domain.model.Offer;
import com.kairosds.infraestructure.database.mapper.OfferEntityMapper;
import com.kairosds.infraestructure.database.model.OfferEntity;
import com.kairosds.infraestructure.database.repository.jpa.OfferRepositoryJpa;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfferRepositoryImplTest {

  @Mock private OfferRepositoryJpa offerRepositoryJpa;

  @Mock private OfferEntityMapper offerEntityMapper;

  @InjectMocks private OfferRepositoryImpl offerRepository;

  private OfferEntity offerEntity;
  private Offer offer;

  @BeforeEach
  void setUp() {
    offerEntity =
        OfferEntity.builder()
            .offerId(1L)
            .brandId(1L)
            .startDate(Timestamp.valueOf("2020-06-14 00:00:00"))
            .endDate(Timestamp.valueOf("2020-12-31 23:59:59"))
            .priceListId(1L)
            .size("00")
            .model("0100")
            .quality("233")
            .priority(1)
            .price(new BigDecimal("35.50"))
            .currencyIso("EUR")
            .build();

    offer =
        Offer.builder()
            .offerId(1L)
            .brandId(1L)
            .startDate("2020-06-14T00.00.00Z")
            .endDate("2020-12-31T23.59.59Z")
            .priceListId(1L)
            .productPartNumber("000100233")
            .priority(1)
            .price(new BigDecimal("35.50"))
            .currencyIso("EUR")
            .build();
  }

  @Test
  void testSaveOffer_Success() {
    when(offerEntityMapper.toOfferEntity(any(Offer.class))).thenReturn(offerEntity);
    when(offerRepositoryJpa.save(any())).thenReturn(offerEntity);
    when(offerEntityMapper.toOffer(any(OfferEntity.class))).thenReturn(offer);

    Offer createdOffer = offerRepository.saveOffer(offer);

    assertNotNull(createdOffer);
    assertEquals(offer.getOfferId(), createdOffer.getOfferId());
    assertEquals(offer.getProductPartNumber(), createdOffer.getProductPartNumber());

    verify(offerEntityMapper, times(1)).toOfferEntity(any(Offer.class));
    verify(offerRepositoryJpa, times(1)).save(any());
    verify(offerEntityMapper, times(1)).toOffer(any(OfferEntity.class));
  }

  @Test
  void testGetOfferById_Success() {
    when(offerRepositoryJpa.findById(1L)).thenReturn(Optional.of(offerEntity));
    when(offerEntityMapper.toOffer(any(OfferEntity.class))).thenReturn(offer);

    Optional<Offer> retrievedOffer = offerRepository.getOfferById(1L);

    assertTrue(retrievedOffer.isPresent());
    assertEquals(offer.getOfferId(), retrievedOffer.get().getOfferId());
    assertEquals(offer.getProductPartNumber(), retrievedOffer.get().getProductPartNumber());

    verify(offerRepositoryJpa, times(1)).findById(1L);
    verify(offerEntityMapper, times(1)).toOffer(any(OfferEntity.class));
  }

  @Test
  void testGetOfferById_NotFound() {
    when(offerRepositoryJpa.findById(1L)).thenReturn(Optional.empty());

    Optional<Offer> retrievedOffer = offerRepository.getOfferById(1L);

    assertTrue(retrievedOffer.isEmpty());

    verify(offerRepositoryJpa, times(1)).findById(1L);
    verify(offerEntityMapper, never()).toOffer(any(OfferEntity.class));
  }

  @Test
  void testGetAllOffers_Success() {
    when(offerRepositoryJpa.findAll()).thenReturn(List.of(offerEntity));
    when(offerEntityMapper.toOffer(any(OfferEntity.class))).thenReturn(offer);

    List<Offer> allOffers = offerRepository.getAllOffers();

    assertNotNull(allOffers);
    assertEquals(1, allOffers.size());
    assertEquals(offer.getOfferId(), allOffers.get(0).getOfferId());
    assertEquals(offer.getProductPartNumber(), allOffers.get(0).getProductPartNumber());

    verify(offerRepositoryJpa, times(1)).findAll();
    verify(offerEntityMapper, times(1)).toOffer(any(OfferEntity.class));
  }

  @Test
  void testDeleteOfferById_Success() {
    doNothing().when(offerRepositoryJpa).deleteById(1L);

    assertDoesNotThrow(() -> offerRepository.deleteOfferById(1L));

    verify(offerRepositoryJpa, times(1)).deleteById(1L);
  }

  @Test
  void testDeleteAllOffers_Success() {
    doNothing().when(offerRepositoryJpa).deleteAllInBatch();

    assertDoesNotThrow(() -> offerRepository.deleteAllOffers());

    verify(offerRepositoryJpa, times(1)).deleteAllInBatch();
  }
}
