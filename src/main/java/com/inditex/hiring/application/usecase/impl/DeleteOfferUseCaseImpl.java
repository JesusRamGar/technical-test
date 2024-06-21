package com.inditex.hiring.application.usecase.impl;

import com.inditex.hiring.domain.service.DeleteOfferService;
import com.inditex.hiring.domain.service.GetOfferService;
import com.inditex.hiring.domain.usecase.DeleteOfferUseCase;
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
