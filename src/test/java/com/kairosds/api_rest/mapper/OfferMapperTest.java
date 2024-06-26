package com.kairosds.api_rest.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.kairosds.domain.model.Offer;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
  void testToOfferDTO_Success() {
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

    OfferDTO offerDTO = mapper.toOfferDTO(offer);

    assertNotNull(offerDTO);
    assertEquals(offer.getOfferId(), offerDTO.getOfferId());
    assertEquals(offer.getBrandId(), offerDTO.getBrandId());
    assertEquals("2020-06-14T00.00.00Z", offerDTO.getStartDate());
    assertEquals("2020-12-31T23.59.59Z", offerDTO.getEndDate());
    assertEquals(offer.getPriceListId(), offerDTO.getPriceListId());
    assertEquals(
        offer.getSize() + offer.getModel() + offer.getQuality(), offerDTO.getProductPartNumber());
    assertEquals(offer.getPriority(), offerDTO.getPriority());
    assertEquals(offer.getPrice().doubleValue(), offerDTO.getPrice());
    assertEquals(offer.getCurrencyIso(), offerDTO.getCurrencyIso());
  }

  @Test
  void testNullValues_Success() {
    Offer offer = new Offer();
    OfferDTO offerDTO = mapper.toOfferDTO(offer);
    assertNotNull(offerDTO);
    assertNull(offerDTO.getOfferId());
    assertNull(offerDTO.getBrandId());
    assertNull(offerDTO.getStartDate());
    assertNull(offerDTO.getEndDate());
    assertNull(offerDTO.getPriceListId());
    assertEquals(offerDTO.getProductPartNumber(), "");
    assertNull(offerDTO.getPriority());
    assertNull(offerDTO.getPrice());
    assertNull(offerDTO.getCurrencyIso());
  }
}
