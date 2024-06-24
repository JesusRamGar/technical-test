package com.kairosds.infraestructure.database.model;

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
  private Long brandId;

  @NotNull
  @Column(name = "START_DATE", nullable = false)
  private Timestamp startDate;

  @NotNull
  @Column(name = "END_DATE", nullable = false)
  private Timestamp endDate;

  @NotNull
  @Column(name = "PRICE_LIST", nullable = false)
  private Long priceListId;

  @NotNull
  @Column(name = "SIZE", nullable = false)
  private String size;

  @NotNull
  @Column(name = "MODEL", nullable = false)
  private String model;

  @NotNull
  @Column(name = "QUALITY", nullable = false)
  private String quality;

  @NotNull
  @Column(name = "PRIORITY", nullable = false)
  private Integer priority;

  @NotNull
  @Column(name = "PRICE", nullable = false)
  private BigDecimal price;

  @NotNull
  @Column(name = "CURRENCY_ISO", nullable = false)
  private String currencyIso;
}
