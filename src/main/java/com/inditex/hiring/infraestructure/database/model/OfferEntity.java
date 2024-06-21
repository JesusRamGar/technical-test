package com.inditex.hiring.infraestructure.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.*;

@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Offer")
public class OfferEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OFFER_ID")
  private Long offerId;

  @NotNull
  @Column(name = "BRAND_ID", nullable = false)
  private Integer brandId;

  @NotNull
  @Column(name = "START_DATE", nullable = false)
  private Timestamp startDate;

  @NotNull
  @Column(name = "END_DATE", nullable = false)
  private Timestamp endDate;

  @Column(name = "PRICE_LIST")
  private Long priceListId;

  @Column(name = "SIZE")
  private String size;

  @Column(name = "MODEL")
  private String model;

  @Column(name = "QUALITY")
  private String quality;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "CURRENCY_ISO")
  private String currencyIso;
}
