package com.kairosds.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.kairosds.domain.model.Offer;
import com.kairosds.domain.repository.OfferRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateOfferServiceImplTest {

  @Mock private OfferRepository offerRepository;

  @InjectMocks private CreateOfferServiceImpl createOfferService;

  @Captor private ArgumentCaptor<Offer> offerCaptor;

  @Test
  void testCreateOffer_Success() {
    Offer offerToCreate = generateOfferExample();
    Offer createdOffer = generateOfferExample();

    when(offerRepository.saveOffer(offerToCreate)).thenReturn(createdOffer);

    Offer result = createOfferService.createOffer(offerToCreate);

    verify(offerRepository).saveOffer(offerToCreate);

    assertEquals(createdOffer, result, "The returned offer must be the same as the one created");
  }

  @Test
  void testCreateOffer_SuccessWithCaptor() {
    Offer offerToCreate = generateOfferExample();

    when(offerRepository.saveOffer(any(Offer.class))).thenReturn(offerToCreate);

    Offer createdOffer = createOfferService.createOffer(offerToCreate);

    verify(offerRepository, times(1)).saveOffer(offerCaptor.capture());

    Offer capturedOffer = offerCaptor.getValue();

    assertEquals(
        offerToCreate, capturedOffer, "The returned offer must be the same as the one created");
    assertEquals(
        createdOffer, capturedOffer, "The returned offer must be the same as the one created");
    assertEquals(offerToCreate.getOfferId(), capturedOffer.getOfferId());
  }

  private Offer generateOfferExample() {
    return Offer.builder()
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
  }
}
