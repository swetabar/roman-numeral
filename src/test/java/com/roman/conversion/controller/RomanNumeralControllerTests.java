package com.roman.conversion.controller;

import com.roman.conversion.model.RomanNumeral;
import com.roman.conversion.service.IRomanNumeralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.MessageFormat;

import static com.roman.conversion.utils.RomanNumeralUtils.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link RomanNumeralController}
 * @author swetabarman
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RomanNumeralController.class)
public class RomanNumeralControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRomanNumeralService romanNumeralService;


    @Test
    public void shouldReturnExpectedRomanNumeral() throws Exception {
        when(romanNumeralService.convertDecimalToRomanNumeral(1)).thenReturn(
                new RomanNumeral("1", "I"));
        this.mockMvc.perform(get("/romannumeral").param(QUERY, "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.input").value("1"))
                .andExpect(jsonPath("$.output").value("I"));
    }

    @Test
    public void shouldThrowIncorrectLimitException() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param(QUERY, "0"))
                .andDo(print()).andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value(ERROR_CODE_REQUEST_LIMIT_ERROR))
                .andExpect(jsonPath("$.message").value(EXCEPTION_MESSAGE_FOR_ZERO));
    }

    @Test
    public void shouldThrowIncorrectLimitExceptionForValueGreaterThanMax() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param(QUERY, "256"))
                .andDo(print()).andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value(ERROR_CODE_REQUEST_LIMIT_ERROR))
                .andExpect(jsonPath("$.message").value(MessageFormat.format(EXCEPTION_MESSAGE_OUTSIDE_LIMIT,
                        MIN_LIMIT, MAX_LIMIT)));
    }

    @Test
    public void shouldThrowIncorrectLimitExceptionForValueLessThanMin() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param(QUERY, "-20"))
                .andDo(print()).andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value(ERROR_CODE_REQUEST_LIMIT_ERROR))
                .andExpect(jsonPath("$.message").value(MessageFormat.format(EXCEPTION_MESSAGE_OUTSIDE_LIMIT,
                        MIN_LIMIT, MAX_LIMIT)));
    }
}
