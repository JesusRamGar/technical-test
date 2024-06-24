package com.kairosds.application.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.kairosds.domain.exception.OfferNotFoundException;
import com.kairosds.domain.model.Offer;
import com.kairosds.domain.repository.OfferRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOfferServiceImplTest {

  @Mock private OfferRepository offerRepository;

  @InjectMocks private GetOfferServiceImpl getOfferService;

  private Offer mockOffer;

  @BeforeEach
  void setUp() {
    mockOffer =
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
  void testGetOfferById_Success() {
    Long offerId = 1L;

    when(offerRepository.getOfferById(offerId)).thenReturn(Optional.of(mockOffer));

    Offer result = getOfferService.getOfferById(offerId);

    assertEquals(mockOffer, result, "The returned offer must be the same as the mockOffer");
  }

  @Test
  void testGetOfferById_NotFound() {
    Long offerId = 2L;

    when(offerRepository.getOfferById(offerId)).thenReturn(Optional.empty());

    OfferNotFoundException ex =
        assertThrows(OfferNotFoundException.class, () -> getOfferService.getOfferById(offerId));
    assertThat(ex.getMessage(), containsString("Offer with id 2 not found"));
  }

  @Test
  void testGetAllOffers_Success() {
    List<Offer> mockOfferList = List.of(mockOffer);

    when(offerRepository.getAllOffers()).thenReturn(mockOfferList);

    List<Offer> result = getOfferService.getAllOffers();

    assertEquals(mockOfferList, result, "The returned offer list must be equal to mockOfferList");
  }
}
