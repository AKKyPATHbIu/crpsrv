package com.epam.crpsrv.web;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class BaseControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    protected <T> T executeGet(String url, Class<T> resultClass) throws Exception {
        return (T) executeGet(url, () -> objectMapper.readerFor(resultClass));
    }

    protected Object executeGet(String url, TypeReference typeReference) throws Exception {
        return executeGet(url, () -> objectMapper.readerFor(typeReference));
    }

    private Object executeGet(String url, Supplier<ObjectReader> objectReaderSupplier) throws Exception {
        var content = getResponse(url);
        return objectReaderSupplier.get().readValue(content);
    }

    private byte[] getResponse(String url) throws Exception {
        var resultAction = mockMvc.perform(get(url).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        return resultAction
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
    }
}