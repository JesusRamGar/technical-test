package com.kairosds.application.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kairosds.domain.model.Offer;
import com.kairosds.domain.repository.OfferRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateOfferServiceImplTest {

  @Mock private OfferRepository offerRepository;

  @InjectMocks private UpdateOfferServiceImpl updateOfferService;

  @Test
  void testUpdateOffer() {
    Offer offer =
        Offer.builder()
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

    when(offerRepository.saveOffer(any(Offer.class))).thenReturn(offer);

    Offer updatedOffer = updateOfferService.updateOffer(offer);

    assertEquals(offer, updatedOffer);
  }
}
