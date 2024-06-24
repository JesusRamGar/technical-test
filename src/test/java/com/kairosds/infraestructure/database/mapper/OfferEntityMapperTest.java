package com.kairosds.infraestructure.database.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.kairosds.domain.model.Offer;
import com.kairosds.infraestructure.database.model.OfferEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OfferEntityMapperTest {

  @Autowired private OfferEntityMapper mapper;

  @Test
  void testToOffer_Success() {
    OfferEntity offerEntity =
        OfferEntity.builder()
            .offerId(1L)
            .brandId(1)
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

    Offer offer = mapper.toOffer(offerEntity);

    assertNotNull(offer);
    assertEquals(offerEntity.getOfferId(), offer.getOfferId());
    assertEquals(offerEntity.getBrandId(), offer.getBrandId());
    assertEquals("2020-06-14T00.00.00Z", offer.getStartDate());
    assertEquals("2020-12-31T23.59.59Z", offer.getEndDate());
    assertEquals(offerEntity.getPriceListId(), offer.getPriceListId());
    assertEquals(
        offerEntity.getSize() + offerEntity.getModel() + offerEntity.getQuality(),
        offer.getProductPartNumber());
    assertEquals(offerEntity.getPriority(), offer.getPriority());
    assertEquals(offerEntity.getPrice(), offer.getPrice());
    assertEquals(offerEntity.getCurrencyIso(), offer.getCurrencyIso());
  }

  @Test
  void testToOfferEntity_Success() {
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

    OfferEntity offerEntity = mapper.toOfferEntity(offer);

    assertNotNull(offerEntity);
    assertEquals(offer.getOfferId(), offerEntity.getOfferId());
    assertEquals(offer.getBrandId(), offerEntity.getBrandId());
    assertEquals(Timestamp.valueOf("2020-06-14 00:00:00"), offerEntity.getStartDate());
    assertEquals(Timestamp.valueOf("2020-12-31 23:59:59"), offerEntity.getEndDate());
    assertEquals(offer.getPriceListId(), offerEntity.getPriceListId());
    assertEquals("00", offerEntity.getSize());
    assertEquals("0100", offerEntity.getModel());
    assertEquals("233", offerEntity.getQuality());
    assertEquals(offer.getPriority(), offerEntity.getPriority());
    assertEquals(offer.getPrice(), offerEntity.getPrice());
    assertEquals(offer.getCurrencyIso(), offerEntity.getCurrencyIso());
  }

  @Test
  void testNullValues_Success() {
    OfferEntity offerEntity = new OfferEntity();
    Offer offer = mapper.toOffer(offerEntity);
    assertNotNull(offer);
    assertNull(offer.getOfferId());
    assertNull(offer.getBrandId());
    assertNull(offer.getStartDate());
    assertNull(offer.getEndDate());
    assertNull(offer.getPriceListId());
    assertEquals(offer.getProductPartNumber(), "");
    assertNull(offer.getPriority());
    assertNull(offer.getPrice());
    assertNull(offer.getCurrencyIso());

    offer = new Offer();
    offerEntity = mapper.toOfferEntity(offer);
    assertNotNull(offerEntity);
    assertNull(offerEntity.getOfferId());
    assertNull(offerEntity.getBrandId());
    assertNull(offerEntity.getStartDate());
    assertNull(offerEntity.getEndDate());
    assertNull(offerEntity.getPriceListId());
    assertNull(offerEntity.getSize());
    assertNull(offerEntity.getModel());
    assertNull(offerEntity.getQuality());
    assertNull(offerEntity.getPriority());
    assertNull(offerEntity.getPrice());
    assertNull(offerEntity.getCurrencyIso());
  }

  @Test
  void testAsTimestamp_InvalidDateFormat() {
    String dateString = "2020-06-14T00-00-00Z";

    Assertions.assertThrows(DateTimeParseException.class, () -> mapper.asTimestamp(dateString));
  }
}
