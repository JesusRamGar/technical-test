package com.kairosds.api_rest.controller;

import com.kairosds.api_rest.mapper.OfferMapper;
import com.kairosds.domain.model.Offer;
import com.kairosds.domain.service.CreateOfferService;
import com.kairosds.domain.service.DeleteOfferService;
import com.kairosds.domain.service.GetOfferService;
import com.kairosds.domain.usecase.DeleteOfferUseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.OfferApi;
import org.openapitools.model.OfferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class OfferController implements OfferApi {

  private final CreateOfferService createOfferService;

  private final GetOfferService getOfferService;

  private final DeleteOfferService deleteOfferService;

  private final DeleteOfferUseCase deleteOfferUseCase;

  private final OfferMapper offerMapper;

  @Override
  public ResponseEntity<OfferDTO> createOffer(OfferDTO offerDto) {
    Offer offer = createOfferService.createOffer(offerMapper.toOffer(offerDto));
    OfferDTO offerDTO = offerMapper.toOfferDTO(offer);
    return ResponseEntity.ok(offerDTO);
  }

  @Override
  public ResponseEntity<Void> deleteAllOffers() {
    deleteOfferService.deleteAllOffers();
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> deleteOfferById(Long id) {
    deleteOfferUseCase.deleteOfferById(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<List<OfferDTO>> getAllOffers() {
    List<Offer> offerList = getOfferService.getAllOffers();
    List<OfferDTO> offerDTOList =
        offerList.stream().map(offerMapper::toOfferDTO).collect(Collectors.toList());
    return ResponseEntity.ok(offerDTOList);
  }

  @Override
  public ResponseEntity<OfferDTO> getOfferById(Long id) {
    Offer offer = getOfferService.getOfferById(id);
    OfferDTO offerDTO = offerMapper.toOfferDTO(offer);
    return ResponseEntity.ok(offerDTO);
  }
}
