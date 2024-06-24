package com.kairosds.api_rest.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.kairosds.domain.model.Offer;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openapitools.model.OfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OfferMapperTest {

  @Autowired private OfferMapper mapper;

  @Test
  void testToOffer_Success() {
    OfferDTO offerDTO =
        new OfferDTO()
            .offerId(1L)
            .brandId(1)
            .startDate("2020-06-14T00.00.00Z")
            .endDate("2020-12-31T23.59.59Z")
            .priceListId(1L)
            .productPartNumber("000100233")
            .priority(1)
            .price(35.50)
            .currencyIso("EUR");

    Offer offer = mapper.toOffer(offerDTO);

    assertNotNull(offer);
    assertEquals(offerDTO.getOfferId(), offer.getOfferId());
    assertEquals(offerDTO.getBrandId(), offer.getBrandId());
    assertEquals(offerDTO.getStartDate(), offer.getStartDate());
    assertEquals(offerDTO.getEndDate(), offer.getEndDate());
    assertEquals(offerDTO.getPriceListId(), offer.getPriceListId());
    assertEquals(offerDTO.getProductPartNumber(), offer.getProductPartNumber());
    assertEquals(offerDTO.getPriority(), offer.getPriority());
    assertEquals(BigDecimal.valueOf(offerDTO.getPrice()), offer.getPrice());
    assertEquals(offerDTO.getCurrencyIso(), offer.getCurrencyIso());
  }

  @Test
  void testToOfferDTO_Success() {
    Offer offer =
        Offer.builder()
            .offerId(1L)
            .brandId(1)
            .startDate("2020-06-14T00.00.00Z")
            .endDate("2020-12-31T23.59.59Z")
            .priceListId(1L)
            .productPartNumber("000100233")
            .priority(1)
            .price(new BigDecimal("35.50"))
            .currencyIso("EUR")
            .build();

    OfferDTO offerDTO = mapper.toOfferDTO(offer);

    assertNotNull(offerDTO);
    assertEquals(offer.getOfferId(), offerDTO.getOfferId());
    assertEquals(offer.getBrandId(), offerDTO.getBrandId());
    assertEquals(offer.getStartDate(), offerDTO.getStartDate());
    assertEquals(offer.getEndDate(), offerDTO.getEndDate());
    assertEquals(offer.getPriceListId(), offerDTO.getPriceListId());
    assertEquals(offer.getProductPartNumber(), offerDTO.getProductPartNumber());
    assertEquals(offer.getPriority(), offerDTO.getPriority());
    assertEquals(offer.getPrice().doubleValue(), offerDTO.getPrice());
    assertEquals(offer.getCurrencyIso(), offerDTO.getCurrencyIso());
  }

  @Test
  void testNullValues_Success() {
    OfferDTO offerDTO = new OfferDTO();
    Offer offer = mapper.toOffer(offerDTO);
    assertNotNull(offer);
    assertNull(offer.getOfferId());
    assertNull(offer.getBrandId());
    assertNull(offer.getStartDate());
    assertNull(offer.getEndDate());
    assertNull(offer.getPriceListId());
    assertNull(offer.getProductPartNumber());
    assertNull(offer.getPriority());
    assertNull(offer.getPrice());
    assertNull(offer.getCurrencyIso());

    offer = new Offer();
    offerDTO = mapper.toOfferDTO(offer);
    assertNotNull(offerDTO);
    assertNull(offerDTO.getOfferId());
    assertNull(offerDTO.getBrandId());
    assertNull(offerDTO.getStartDate());
    assertNull(offerDTO.getEndDate());
    assertNull(offerDTO.getPriceListId());
    assertNull(offerDTO.getProductPartNumber());
    assertNull(offerDTO.getPriority());
    assertNull(offerDTO.getPrice());
    assertNull(offerDTO.getCurrencyIso());
  }
}
