package com.challenge.pricing.service.unit.infrastructure.controller;

import com.challenge.pricing.service.infrastructure.controller.dto.ErrorResponse;
import com.challenge.pricing.service.application.exception.RepositoryException;
import com.challenge.pricing.service.application.port.in.GetAppliedPriceUseCase;
import com.challenge.pricing.service.infrastructure.controller.PriceController;
import com.challenge.pricing.service.infrastructure.mapper.PriceMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetAppliedPriceUseCase getAppliedPriceUseCaseMock;

    @MockitoBean
    private PriceMapper priceMapperMock;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Test
    @DisplayName("Test GET /v1/prices/applied WHEN useCase returns RepositoryException THEN expect status 503")
    public void getPrice_repositoryException() throws Exception {

        long productId = 1L;
        long brandId = 2L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T21:00:00");
        Mockito.when(getAppliedPriceUseCaseMock.getAppliedPrice(productId, applicationDate, brandId))
                .thenThrow(new RepositoryException("Oops, something went wrong.", new RuntimeException()));

        MvcResult result = mockMvc.perform(
                        get("/v1/prices/applied")
                                .param("productId", String.valueOf(productId))
                                .param("brandId", String.valueOf(brandId))
                                .param("applicationDate", String.valueOf(applicationDate))
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isServiceUnavailable())
                .andReturn();

        Assertions.assertEquals(503, result.getResponse().getStatus());
        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertNotNull(responseBody);
        ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
        Assertions.assertEquals("Database connection failed or persistence error occurred.", errorResponse.getMessage());

    }

}
