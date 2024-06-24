package com.kairosds.api_rest.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.kairosds.domain.model.Offer;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openapitools.model.OfferRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OfferRequestMapperTest {

  @Autowired private OfferRequestMapper mapper;

  @Test
  void testToOffer_Success() {
    OfferRequestDTO offerRequestDTO =
        new OfferRequestDTO()
            .brandId(1L)
            .startDate("2020-06-14T00.00.00Z")
            .endDate("2020-12-31T23.59.59Z")
            .priceListId(1L)
            .productPartNumber("000100233")
            .priority(1)
            .price(35.50)
            .currencyIso("EUR");

    Offer offer = mapper.toOffer(offerRequestDTO);

    assertNotNull(offer);
    assertEquals(offerRequestDTO.getBrandId(), offer.getBrandId());
    assertEquals(offerRequestDTO.getStartDate(), offer.getStartDate());
    assertEquals(offerRequestDTO.getEndDate(), offer.getEndDate());
    assertEquals(offerRequestDTO.getPriceListId(), offer.getPriceListId());
    assertEquals(offerRequestDTO.getProductPartNumber(), offer.getProductPartNumber());
    assertEquals(offerRequestDTO.getPriority(), offer.getPriority());
    assertEquals(BigDecimal.valueOf(offerRequestDTO.getPrice()), offer.getPrice());
    assertEquals(offerRequestDTO.getCurrencyIso(), offer.getCurrencyIso());
  }

  @Test
  void testNullValues_Success() {
    OfferRequestDTO offerRequestDTO = new OfferRequestDTO();
    Offer offer = mapper.toOffer(offerRequestDTO);
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
  }
}
