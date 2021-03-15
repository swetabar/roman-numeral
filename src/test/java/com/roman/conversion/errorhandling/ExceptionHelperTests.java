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

import java.text.MessageFormat;

import static com.roman.conversion.utils.RomanNumeralUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Unit tests for {@link ExceptionHelper}
 * @author swetabarman
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
        String input = "9.5";
        this.mockMvc.perform(get("/romannumeral").param(QUERY, input))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value(ERROR_CODE_INVALID_INPUT))
                .andExpect(jsonPath("$.message").value(MessageFormat.format(EXCEPTION_MESSAGE_DOUBLE_INPUT, input) + BLANK +
                        MessageFormat.format(VALID_MESSAGE, MIN_LIMIT
                                , MAX_LIMIT)));
    }

    @Test
    public void shouldReturnEmptyQueryErrorMessage() throws Exception {
        this.mockMvc.perform(get("/romannumeral"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value(ERROR_CODE_EMPTY_REQUEST))
                .andExpect(jsonPath("$.message").value(MessageFormat.format(EXCEPTION_MESSAGE_EMPTY_REQUEST, QUERY)));
    }

    @Test
    public void shouldReturnIncorrectLimitErrorMessage() throws Exception {
        this.mockMvc.perform(get("/romannumeral").param(QUERY, "-90"))
                .andDo(print()).andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorCode").value(ERROR_CODE_REQUEST_LIMIT_ERROR))
                .andExpect(jsonPath("$.message").value(MessageFormat.format(EXCEPTION_MESSAGE_OUTSIDE_LIMIT,
                        MIN_LIMIT, MAX_LIMIT)));
    }

}
