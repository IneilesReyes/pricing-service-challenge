package com.challenge.pricing.service.integration;

import com.challenge.pricing.service.infrastructure.controller.dto.ErrorResponse;
import com.challenge.pricing.service.infrastructure.controller.dto.PriceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PriceControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private Integer port;

    @Test
    @DisplayName("Test 1: Price for product 35455, brand ZARA at 14th June 10:00")
    void testPriceAtMorning1() {
        String applicationDateAsString = "2020-06-14T10:00:00";
        LocalDateTime applicationDate = LocalDateTime.parse(applicationDateAsString);
        String url = buildPath("prices/applied?productId=35455&applicationDate=%s&brandId=1".formatted(applicationDateAsString));
        ResponseEntity<PriceResponse> responseEntity = testRestTemplate.getForEntity(url, PriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        PriceResponse priceResponse = responseEntity.getBody();
        Assertions.assertEquals(1, priceResponse.getBrandId());
        Assertions.assertEquals(35455, priceResponse.getProductId());
        Assertions.assertEquals(new BigDecimal("35.50"), priceResponse.getPrice());
        Assertions.assertNotNull(priceResponse.getCurrency());
        Assertions.assertTrue(priceResponse.getStartDate().isBefore(applicationDate));
        Assertions.assertTrue(priceResponse.getEndDate().isAfter(applicationDate));
    }

    @Test
    @DisplayName("Test 2: Price for product 35455, brand ZARA at 14th June 16:00")
    void testPriceAtAfternoon1() {
        String applicationDateAsString = "2020-06-14T16:00:00";
        LocalDateTime applicationDate = LocalDateTime.parse(applicationDateAsString);
        String url = buildPath("prices/applied?productId=35455&applicationDate=%s&brandId=1".formatted(applicationDateAsString));
        ResponseEntity<PriceResponse> responseEntity = testRestTemplate.getForEntity(url, PriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        PriceResponse priceResponse = responseEntity.getBody();
        Assertions.assertEquals(1, priceResponse.getBrandId());
        Assertions.assertEquals(35455, priceResponse.getProductId());
        Assertions.assertEquals(BigDecimal.valueOf(25.45), priceResponse.getPrice());
        Assertions.assertNotNull(priceResponse.getCurrency());
        Assertions.assertTrue(priceResponse.getStartDate().isBefore(applicationDate));
        Assertions.assertTrue(priceResponse.getEndDate().isAfter(applicationDate));
    }

    @Test
    @DisplayName("Test 3: Price for product 35455, brand ZARA at 14th June 21:00")
    void testPriceAtNight1() {
        String applicationDateAsString = "2020-06-14T21:00:00";
        LocalDateTime applicationDate = LocalDateTime.parse(applicationDateAsString);
        String url = buildPath("prices/applied?productId=35455&applicationDate=%s&brandId=1".formatted(applicationDateAsString));
        ResponseEntity<PriceResponse> responseEntity = testRestTemplate.getForEntity(url, PriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        PriceResponse priceResponse = responseEntity.getBody();
        Assertions.assertEquals(1, priceResponse.getBrandId());
        Assertions.assertEquals(35455, priceResponse.getProductId());
        Assertions.assertEquals(new BigDecimal("35.50"), priceResponse.getPrice());
        Assertions.assertNotNull(priceResponse.getCurrency());
        Assertions.assertTrue(priceResponse.getStartDate().isBefore(applicationDate));
        Assertions.assertTrue(priceResponse.getEndDate().isAfter(applicationDate));
    }

    @Test
    @DisplayName("Test 4: Price for product 35455, brand ZARA at 15th June 10:00")
    void testPriceAtMorning2() {
        String applicationDateAsString = "2020-06-15T10:00:00";
        LocalDateTime applicationDate = LocalDateTime.parse(applicationDateAsString);
        String url = buildPath("prices/applied?productId=35455&applicationDate=%s&brandId=1".formatted(applicationDateAsString));
        ResponseEntity<PriceResponse> responseEntity = testRestTemplate.getForEntity(url, PriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        PriceResponse priceResponse = responseEntity.getBody();
        Assertions.assertEquals(1, priceResponse.getBrandId());
        Assertions.assertEquals(35455, priceResponse.getProductId());
        Assertions.assertEquals(new BigDecimal("30.50"), priceResponse.getPrice());
        Assertions.assertNotNull(priceResponse.getCurrency());
        Assertions.assertTrue(priceResponse.getStartDate().isBefore(applicationDate));
        Assertions.assertTrue(priceResponse.getEndDate().isAfter(applicationDate));
    }

    @Test
    @DisplayName("Test 5: Price for product 35455, brand ZARA at 16th June 21:00")
    void testPriceAtNight2() {
        String applicationDateAsString = "2020-06-14T21:00:00";
        LocalDateTime applicationDate = LocalDateTime.parse(applicationDateAsString);
        String url = buildPath("prices/applied?productId=35455&applicationDate=%s&brandId=1".formatted(applicationDateAsString));
        ResponseEntity<PriceResponse> responseEntity = testRestTemplate.getForEntity(url, PriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        PriceResponse priceResponse = responseEntity.getBody();
        Assertions.assertEquals(1, priceResponse.getBrandId());
        Assertions.assertEquals(35455, priceResponse.getProductId());
        Assertions.assertEquals(new BigDecimal("35.50"), priceResponse.getPrice());
        Assertions.assertNotNull(priceResponse.getCurrency());
        Assertions.assertTrue(priceResponse.getStartDate().isBefore(applicationDate));
        Assertions.assertTrue(priceResponse.getEndDate().isAfter(applicationDate));
    }

    @Test
    @DisplayName("Test invalid parameter type: WHEN inserts an invalid parameter type THEN throws MethodArgumentTypeMismatchException")
    void testInvalidParameterType() {
        String url = buildPath("prices/applied?productId=sarasa&applicationDate=2020-06-14T10:00:00&brandId=1");
        ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity(url, ErrorResponse.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse error = response.getBody();
        Assertions.assertNotNull(error);
        Assertions.assertTrue(error.getMessage().contains("Invalid value"));
        Assertions.assertNotNull(error.getTimestamp());
    }

    @Test
    @DisplayName("Test missing parameter: WHEN there are missing values THEN throws MissingServletRequestParameterException")
    void testMissingParameter() {
        String url = buildPath("prices/applied?productId=35455&applicationDate=2020-06-14T10:00:00");
        ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity(url, ErrorResponse.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse error = response.getBody();
        Assertions.assertNotNull(error);
        Assertions.assertTrue(error.getMessage().contains("Missing value for parameter"));
        Assertions.assertNotNull(error.getTimestamp());
    }

    @Test
    @DisplayName("Test resource not found: WHEN the price query returns null THEN throws ResourceNotFoundException")
    void testResourceNotFound() {
        String url = buildPath("prices/applied?productId=35455&applicationDate=2020-06-14T10:00:00&brandId=999");
        ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity(url, ErrorResponse.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = response.getBody();
        Assertions.assertNotNull(error);
        Assertions.assertEquals("There are no prices found - productId: 35455 - brandId: 999 - date: 2020-06-14T10:00", error.getMessage());
        Assertions.assertNotNull(error.getTimestamp());
    }

    @Test
    @DisplayName("Test invalid URL: WHEN the URL path is incorrect THEN throws NoResourceFoundException")
    void testInvalidUrl() {
        String url = buildPath("non-existent-endpoint");
        ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity(url, ErrorResponse.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = response.getBody();
        Assertions.assertNotNull(error);
        Assertions.assertEquals("The requested resource was not found.", error.getMessage());
        Assertions.assertNotNull(error.getTimestamp());
    }



    private String buildPath(String path) {
        return "http://localhost:%s/pricing-service/v1/%s".formatted(port, path);
    }

}
