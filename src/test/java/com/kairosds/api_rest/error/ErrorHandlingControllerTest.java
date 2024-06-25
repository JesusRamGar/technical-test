package com.kairosds.api_rest.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.kairosds.domain.exception.OfferNotFoundException;
import java.nio.file.AccessDeniedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ExtendWith(MockitoExtension.class)
class ErrorHandlingControllerTest {

  @InjectMocks private ErrorHandlingController errorHandlingController;

  @Test
  void testGenericExceptionHandler() {
    Exception exception = new RuntimeException("Internal server error");
    ResponseEntity<ErrorResponseDTO> response =
        errorHandlingController.genericExceptionHandler(exception);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getCode());
    assertEquals(
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), response.getBody().getMessage());
    assertEquals(exception.getMessage(), response.getBody().getDetail());
  }

  @Test
  void testNotAuthorizedExceptionHandler() {
    AccessDeniedException exception = new AccessDeniedException("Not authorized");
    ResponseEntity<ErrorResponseDTO> response =
        errorHandlingController.notAuthorizedExceptionHandler(exception);

    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getBody().getCode());
    assertEquals(HttpStatus.UNAUTHORIZED.getReasonPhrase(), response.getBody().getMessage());
    assertEquals(exception.getMessage(), response.getBody().getDetail());
  }

  @Test
  void testBadRequestExceptionHandler() {
    MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
    Mockito.when(exception.getMessage()).thenReturn("Bad request");
    ResponseEntity<ErrorResponseDTO> response =
        errorHandlingController.badRequestExceptionHandler(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getCode());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getBody().getMessage());
    assertEquals(exception.getMessage(), response.getBody().getDetail());
  }

  @Test
  void testNotFoundExceptionHandler() {
    NoHandlerFoundException exception = mock(NoHandlerFoundException.class);
    Mockito.when(exception.getMessage()).thenReturn("Not found");
    ResponseEntity<ErrorResponseDTO> response =
        errorHandlingController.notFoundExceptionHandler(exception);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getCode());
    assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), response.getBody().getMessage());
    assertEquals(exception.getMessage(), response.getBody().getDetail());
  }

  @Test
  void testMethodNotAllowedExceptionHandler() {
    HttpRequestMethodNotSupportedException exception =
        new HttpRequestMethodNotSupportedException("POST");
    ResponseEntity<ErrorResponseDTO> response =
        errorHandlingController.methodNotAllowedExceptionHandler(exception);

    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getBody().getCode());
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), response.getBody().getMessage());
    assertEquals(exception.getMessage(), response.getBody().getDetail());
  }

  @Test
  void testOfferNotFoundExceptionHandler() {
    OfferNotFoundException exception = new OfferNotFoundException("Offer not found");
    ResponseEntity<ErrorResponseDTO> response =
        errorHandlingController.offerNotFoundExceptionHandler(exception);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getCode());
    assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), response.getBody().getMessage());
    assertEquals(exception.getMessage(), response.getBody().getDetail());
  }
}
