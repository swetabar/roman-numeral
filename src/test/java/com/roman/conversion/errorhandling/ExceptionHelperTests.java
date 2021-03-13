package com.roman.conversion.errorhandling;

import com.roman.conversion.controller.RomanNumeralController;
import com.roman.conversion.service.IRomanNumeralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RomanNumeralController.class)
public class ExceptionHelperTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRomanNumeralService romanNumeralService;

    @Test
    public void shouldReturnDoubleValueErrorMessage() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param("query", "9.5"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value("INVALID_INPUT"))
                .andExpect(jsonPath("$.message").value("You entered 9.5, " +
                        "which is a " +
                        "double value. Please enter an integer " +
                                "value in the range of 1 - 255."));
    }

    @Test
    public void shouldReturnEmptyQueryErrorMessage() throws Exception {
        this.mockMvc.perform(get("/romannumeral"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value("REQUEST_IS_EMPTY"))
                .andExpect(jsonPath("$.message").value("query parameter is " +
                        "blank."));
    }

    @Test
    public void shouldReturnIncorrectLimitErrorMessage() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param("query", "-90"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value("REQUEST_LIMIT_ERROR"))
                .andExpect(jsonPath("$.message").value("The number should " +
                        "lie within the limit of 1 - 255."));
    }

}
