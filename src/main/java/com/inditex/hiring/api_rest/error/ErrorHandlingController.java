package com.inditex.hiring.api_rest.error;

import com.inditex.hiring.domain.exception.OfferNotFoundException;
import java.nio.file.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ErrorResponseDTO;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class ErrorHandlingController {

  private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error - {}";
  private static final String NOT_AUTHORIZED_MESSAGE = "Not Authorized - {}";
  private static final String BAD_REQUEST_MESSAGE = "Bad request - {}";
  private static final String NOT_FOUND_MESSAGE = "Not found - {}";
  private static final String METHOD_NOT_ALLOWED_MESSAGE = "Method not allowed - {}";
  private static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE = "Unsupported media type - {}";
  private static final String NOT_ACCEPTABLE_MESSAGE = "Not acceptable - {}";

  private ResponseEntity<ErrorResponseDTO> getResponse(
      HttpStatus status, Integer code, String message, String detail) {
    int finalCode = code == null ? status.value() : code;
    String finalMessage = StringUtils.hasText(message) ? message : status.getReasonPhrase();
    ErrorResponseDTO errorResponseDTO =
        new ErrorResponseDTO().code(finalCode).message(finalMessage).detail(detail);
    return ResponseEntity.status(status).body(errorResponseDTO);
  }

  @ExceptionHandler({
    RuntimeException.class,
    Exception.class,
    MissingPathVariableException.class,
    ConversionNotSupportedException.class
  })
  public ResponseEntity<ErrorResponseDTO> genericExceptionHandler(Exception ex) {
    log.error(INTERNAL_SERVER_ERROR_MESSAGE, ex.getMessage(), ex);
    return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, "", ex.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponseDTO> notAuthorizedExceptionHandler(Exception ex) {
    log.error(NOT_AUTHORIZED_MESSAGE, ex.getMessage(), ex);
    return getResponse(HttpStatus.UNAUTHORIZED, null, "", ex.getMessage());
  }

  @ExceptionHandler({
    MissingServletRequestParameterException.class,
    TypeMismatchException.class,
    HttpMessageNotReadableException.class,
    MethodArgumentNotValidException.class,
    ServerWebInputException.class,
    DataIntegrityViolationException.class,
    IllegalArgumentException.class,
    MethodArgumentTypeMismatchException.class
  })
  public ResponseEntity<ErrorResponseDTO> badRequestExceptionHandler(Exception ex) {
    log.error(BAD_REQUEST_MESSAGE, ex.getMessage(), ex);
    return getResponse(HttpStatus.BAD_REQUEST, null, "", ex.getMessage());
  }

  @ExceptionHandler({EmptyResultDataAccessException.class, NoHandlerFoundException.class})
  public ResponseEntity<ErrorResponseDTO> notFoundExceptionHandler(Exception ex) {
    log.error(NOT_FOUND_MESSAGE, ex.getMessage(), ex);
    return getResponse(HttpStatus.NOT_FOUND, null, "", ex.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponseDTO> methodNotAllowedExceptionHandler(Exception ex) {
    log.error(METHOD_NOT_ALLOWED_MESSAGE, ex.getMessage(), ex);
    return getResponse(HttpStatus.METHOD_NOT_ALLOWED, null, "", ex.getMessage());
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponseDTO> unsupportedMediaTypeHandler(Exception ex) {
    log.error(UNSUPPORTED_MEDIA_TYPE_MESSAGE, ex.getMessage(), ex);
    return getResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, null, "", ex.getMessage());
  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  public ResponseEntity<ErrorResponseDTO> notAcceptableHandler(Exception ex) {
    log.error(NOT_ACCEPTABLE_MESSAGE, ex.getMessage(), ex);
    return getResponse(HttpStatus.NOT_ACCEPTABLE, null, "", ex.getMessage());
  }

  // Offer Exceptions

  @ExceptionHandler(OfferNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> offerNotFoundExceptionHandler(OfferNotFoundException e) {
    log.error(NOT_FOUND_MESSAGE, e.getMessage());
    return getResponse(HttpStatus.NOT_FOUND, null, "", e.getMessage());
  }
}
