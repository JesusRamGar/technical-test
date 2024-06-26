package com.kairosds.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.*;

@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer implements Serializable {

  private static final long serialVersionUID = 448171649369562796L;

  private Long offerId;

  private Long brandId;

  private Timestamp startDate;

  private Timestamp endDate;

  private Long priceListId;

  private String size;

  private String model;

  private String quality;

  private Integer priority;

  private BigDecimal price;

  private String currencyIso;
}
