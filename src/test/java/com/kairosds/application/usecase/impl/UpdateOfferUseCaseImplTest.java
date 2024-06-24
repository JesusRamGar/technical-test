package com.kairosds.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.kairosds.domain.exception.OfferNotFoundException;
import com.kairosds.domain.model.Offer;
import com.kairosds.domain.service.GetOfferService;
import com.kairosds.domain.service.UpdateOfferService;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateOfferUseCaseImplTest {

  @Mock private GetOfferService getOfferService;

  @Mock private UpdateOfferService updateOfferService;

  @InjectMocks private UpdateOfferUseCaseImpl updateOfferUseCase;

  private Offer offer;

  @BeforeEach
  void setUp() {
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
  void testUpdateOffer() {
    when(getOfferService.getOfferById(offer.getOfferId())).thenReturn(null);

    updateOfferUseCase.updateOffer(offer.getOfferId(), offer);

    verify(getOfferService, times(1)).getOfferById(offer.getOfferId());
    verify(updateOfferService, times(1)).updateOffer(offer);
  }

  @Test
  void testDeleteOfferById_NotFound() {
    when(getOfferService.getOfferById(99L))
        .thenThrow(new OfferNotFoundException("Offer with id 99 not found"));

    assertThrows(OfferNotFoundException.class, () -> updateOfferUseCase.updateOffer(99L, offer));

    verify(getOfferService, times(1)).getOfferById(99L);
    verify(updateOfferService, never()).updateOffer(offer);
  }
}
