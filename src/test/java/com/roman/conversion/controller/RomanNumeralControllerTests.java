package com.roman.conversion.controller;

import com.roman.conversion.errorhandling.IncorrectLimitException;
import com.roman.conversion.model.RomanNumeral;
import com.roman.conversion.service.IRomanNumeralService;
import com.roman.conversion.service.RomanNumeralService;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
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
        this.mockMvc.perform(get("/romannumeral").param("query", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.input").value("1"))
                .andExpect(jsonPath("$.output").value("I"));
    }

    @Test
    public void shouldThrowIncorrectLimitException() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param("query", "0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value("REQUEST_LIMIT_ERROR"))
                .andExpect(jsonPath("$.message").value("The number entered " +
                        "was 0. There is no roman numeral for number 0"));
    }

    @Test
    public void shouldThrowIncorrectLimitExceptionForValueGreaterThanMax() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param("query", "256"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value("REQUEST_LIMIT_ERROR"))
                .andExpect(jsonPath("$.message").value("The number should lie within the limit of 1 - 255."));
    }

    @Test
    public void shouldThrowIncorrectLimitExceptionForValueLessThanMin() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param("query", "-20"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value("REQUEST_LIMIT_ERROR"))
                .andExpect(jsonPath("$.message").value("The number should lie within the limit of 1 - 255."));
    }

}
