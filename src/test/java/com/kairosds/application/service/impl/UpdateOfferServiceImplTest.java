package com.kairosds.application.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kairosds.domain.model.Offer;
import com.kairosds.domain.repository.OfferRepository;
import java.math.BigDecimal;
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
    Offer offer = new Offer();
    offer.setOfferId(1L);
    offer.setBrandId(1L);
    offer.setStartDate("2020-06-14T00.00.00Z");
    offer.setEndDate("2020-12-31T23.59.59Z");
    offer.setPriceListId(1L);
    offer.setProductPartNumber("000100233");
    offer.setPriority(0);
    offer.setPrice(BigDecimal.valueOf(35.50));
    offer.setCurrencyIso("EUR");

    when(offerRepository.saveOffer(any(Offer.class))).thenReturn(offer);

    Offer updatedOffer = updateOfferService.updateOffer(offer);

    assertEquals(offer, updatedOffer);
  }
}
