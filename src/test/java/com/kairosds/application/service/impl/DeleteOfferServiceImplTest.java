package com.kairosds.application.service.impl;

import static org.mockito.Mockito.*;

import com.kairosds.domain.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteOfferServiceImplTest {

  @Mock private OfferRepository offerRepository;

  @InjectMocks private DeleteOfferServiceImpl deleteOfferService;

  @Test
  void testDeleteOfferById_Success() {
    Long offerId = 1L;

    doNothing().when(offerRepository).deleteOfferById(offerId);

    deleteOfferService.deleteOfferById(offerId);

    verify(offerRepository, times(1)).deleteOfferById(offerId);
  }

  @Test
  void testDeleteAllOffers_Success() {
    doNothing().when(offerRepository).deleteAllOffers();

    deleteOfferService.deleteAllOffers();

    verify(offerRepository, times(1)).deleteAllOffers();
  }
}
