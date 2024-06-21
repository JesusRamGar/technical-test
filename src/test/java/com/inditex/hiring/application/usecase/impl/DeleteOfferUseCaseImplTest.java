package com.inditex.hiring.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.inditex.hiring.domain.exception.OfferNotFoundException;
import com.inditex.hiring.domain.service.DeleteOfferService;
import com.inditex.hiring.domain.service.GetOfferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteOfferUseCaseImplTest {

  @Mock private GetOfferService getOfferService;

  @Mock private DeleteOfferService deleteOfferService;

  @InjectMocks private DeleteOfferUseCaseImpl deleteOfferUseCase;

  @Test
  void testDeleteOfferById() {
    Long id = 1L;

    when(getOfferService.getOfferById(id)).thenReturn(null);

    deleteOfferUseCase.deleteOfferById(id);

    verify(getOfferService, times(1)).getOfferById(id);
    verify(deleteOfferService, times(1)).deleteOfferById(id);
  }

  @Test
  void testDeleteOfferById_NotFound() {
    Long id = 1L;

    when(getOfferService.getOfferById(id))
        .thenThrow(new OfferNotFoundException("Offer with id 1 not found"));

    assertThrows(OfferNotFoundException.class, () -> deleteOfferUseCase.deleteOfferById(id));

    verify(getOfferService, times(1)).getOfferById(id);
    verify(deleteOfferService, never()).deleteOfferById(id);
  }
}
