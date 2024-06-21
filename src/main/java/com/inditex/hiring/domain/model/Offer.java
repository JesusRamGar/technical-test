package com.inditex.hiring.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.*;

@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer implements Serializable {

  private static final long serialVersionUID = 448171649369562796L;

  private Long offerId;

  private Integer brandId;

  private String startDate;

  private String endDate;

  private Long priceListId;

  private String productPartNumber;

  private Integer priority;

  private BigDecimal price;

  private String currencyIso;
}
