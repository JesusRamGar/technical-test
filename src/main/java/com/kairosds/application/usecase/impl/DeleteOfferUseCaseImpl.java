package com.kairosds.application.usecase.impl;

import com.kairosds.domain.service.DeleteOfferService;
import com.kairosds.domain.service.GetOfferService;
import com.kairosds.domain.usecase.DeleteOfferUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DeleteOfferUseCaseImpl implements DeleteOfferUseCase {

  private final GetOfferService getOfferService;

  private final DeleteOfferService deleteOfferService;

  @Override
  public void deleteOfferById(Long id) {
    getOfferService.getOfferById(id);

    deleteOfferService.deleteOfferById(id);
  }
}
