package com.kairosds.api_rest.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kairosds.api_rest.error.ErrorHandlingController;
import com.kairosds.api_rest.mapper.OfferMapper;
import com.kairosds.domain.exception.OfferNotFoundException;
import com.kairosds.domain.model.Offer;
import com.kairosds.domain.service.CreateOfferService;
import com.kairosds.domain.service.DeleteOfferService;
import com.kairosds.domain.service.GetOfferService;
import com.kairosds.domain.usecase.DeleteOfferUseCase;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.ErrorResponseDTO;
import org.openapitools.model.OfferDTO;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class OfferControllerTest {
  private MockMvc mockMvc;

  @Mock private CreateOfferService createOfferService;

  @Mock private GetOfferService getOfferService;

  @Mock private DeleteOfferService deleteOfferService;

  @Mock private DeleteOfferUseCase deleteOfferUseCase;

  @Mock private OfferMapper offerMapper;

  @InjectMocks private OfferController offerController;

  private final ObjectMapper objectMapper = new ObjectMapper();

  private Offer offer;
  private OfferDTO offerDTO;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(offerController)
            .setControllerAdvice(new ErrorHandlingController())
            .build();

    offer =
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

    offerDTO =
        new OfferDTO()
            .offerId(1L)
            .brandId(1)
            .startDate("2020-06-14T00.00.00Z")
            .endDate("2020-12-31T23.59.59Z")
            .priceListId(1L)
            .productPartNumber("000100233")
            .priority(1)
            .price(35.50)
            .currencyIso("EUR");
  }

  @Test
  void testCreateOffer() throws Exception {
    when(offerMapper.toOffer(any(OfferDTO.class))).thenReturn(offer);
    when(createOfferService.createOffer(any(Offer.class))).thenReturn(offer);
    when(offerMapper.toOfferDTO(any(Offer.class))).thenReturn(offerDTO);

    mockMvc
        .perform(
            post("/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(offerDTO)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.offerId").value(offerDTO.getOfferId()));

    verify(createOfferService).createOffer(offer);
    verify(offerMapper).toOfferDTO(offer);
  }

  @Test
  void testCreateOffer_BadRequest_IncorrectJSON() throws Exception {
    MvcResult result =
        mockMvc
            .perform(
                post("/offer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                            {"incorrectField":null}
                            """))
            .andExpect(status().isBadRequest())
            .andReturn();

    ErrorResponseDTO response =
        objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

    assertThat(response.getCode(), is(400));
    assertThat(response.getDetail(), containsString("Validation failed"));
  }

  @Test
  void testCreateOffer_BadRequest_ValidField() throws Exception {
    OfferDTO invalidOfferDTO =
        new OfferDTO()
            .brandId(1)
            .startDate("2020-06-14T00.00.00Z")
            .endDate("2020-12-31T23.59.59Z")
            .productPartNumber("0123456789");
    MvcResult result =
        mockMvc
            .perform(
                post("/offer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidOfferDTO)))
            .andExpect(status().isBadRequest())
            .andReturn();

    ErrorResponseDTO response =
        objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

    assertThat(response.getCode(), is(400));
    assertThat(response.getDetail(), containsString("Validation failed"));
    assertThat(response.getDetail(), containsString("productPartNumber"));
    assertThat(response.getDetail(), containsString("size must be between 0 and 9"));
  }

  @Test
  void testDeleteAllOffers() throws Exception {
    doNothing().when(deleteOfferService).deleteAllOffers();

    mockMvc.perform(delete("/offer")).andExpect(status().isNoContent());

    verify(deleteOfferService).deleteAllOffers();
  }

  @Test
  void testDeleteOfferById() throws Exception {
    doNothing().when(deleteOfferUseCase).deleteOfferById(anyLong());

    mockMvc.perform(delete("/offer/{id}", 1L)).andExpect(status().isNoContent());

    verify(deleteOfferUseCase).deleteOfferById(1L);
  }

  @Test
  void testDeleteOfferById_NotFound() throws Exception {
    doThrow(new OfferNotFoundException("Offer with id N not found"))
        .when(deleteOfferUseCase)
        .deleteOfferById(anyLong());

    MvcResult result =
        mockMvc.perform(delete("/offer/{id}", 1L)).andExpect(status().isNotFound()).andReturn();

    ErrorResponseDTO response =
        objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

    assertThat(response.getCode(), is(404));
    assertThat(response.getDetail(), is("Offer with id N not found"));
  }

  @Test
  void testGetAllOffers() throws Exception {
    List<Offer> offers = List.of(offer);
    List<OfferDTO> offerDTOs = List.of(offerDTO);

    when(getOfferService.getAllOffers()).thenReturn(offers);
    when(offerMapper.toOfferDTO(any(Offer.class))).thenReturn(offerDTO);

    mockMvc
        .perform(get("/offer"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(offerDTOs.size())))
        .andExpect(jsonPath("$[0].offerId").value(offerDTO.getOfferId()));

    verify(getOfferService).getAllOffers();
    verify(offerMapper, times(offers.size())).toOfferDTO(any(Offer.class));
  }

  @Test
  void testGetOfferById() throws Exception {
    when(getOfferService.getOfferById(anyLong())).thenReturn(offer);
    when(offerMapper.toOfferDTO(any(Offer.class))).thenReturn(offerDTO);

    mockMvc
        .perform(get("/offer/{id}", 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.offerId").value(offerDTO.getOfferId()));

    verify(getOfferService).getOfferById(1L);
    verify(offerMapper).toOfferDTO(offer);
  }

  @Test
  void testGetOfferById_NotFound() throws Exception {
    when(getOfferService.getOfferById(anyLong()))
        .thenThrow(new OfferNotFoundException("Offer with id N not found"));

    MvcResult result =
        mockMvc.perform(get("/offer/{id}", 1L)).andExpect(status().isNotFound()).andReturn();

    ErrorResponseDTO response =
        objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

    assertThat(response.getCode(), is(404));
    assertThat(response.getDetail(), is("Offer with id N not found"));
  }
}
