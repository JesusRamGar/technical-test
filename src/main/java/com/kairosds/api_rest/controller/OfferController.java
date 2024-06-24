package com.kairosds.api_rest.controller;

import com.kairosds.api_rest.mapper.OfferMapper;
import com.kairosds.api_rest.mapper.OfferRequestMapper;
import com.kairosds.domain.model.Offer;
import com.kairosds.domain.service.CreateOfferService;
import com.kairosds.domain.service.DeleteOfferService;
import com.kairosds.domain.service.GetOfferService;
import com.kairosds.domain.usecase.UpdateOfferUseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.OfferApi;
import org.openapitools.model.OfferDTO;
import org.openapitools.model.OfferRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class OfferController implements OfferApi {

  private final CreateOfferService createOfferService;

  private final GetOfferService getOfferService;

  private final DeleteOfferService deleteOfferService;

  private final UpdateOfferUseCase updateOfferUseCase;

  private final OfferMapper offerMapper;

  private final OfferRequestMapper offerRequestMapper;

  @Override
  public ResponseEntity<OfferDTO> createOffer(OfferRequestDTO offerRequestDTO) {
    Offer offer = createOfferService.createOffer(offerRequestMapper.toOffer(offerRequestDTO));
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
    deleteOfferService.deleteOfferById(id);
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

  @Override
  public ResponseEntity<OfferDTO> updateOfferById(Long id, OfferRequestDTO offerRequestDTO) {
    Offer offer = updateOfferUseCase.updateOffer(id, offerRequestMapper.toOffer(offerRequestDTO));
    OfferDTO offerDTO = offerMapper.toOfferDTO(offer);
    return ResponseEntity.ok(offerDTO);
  }
}
