package com.oocl.web.sampleWebApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class MessageResourceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Test
    public void should_get_hello_message_simple_case() throws Exception{

        final MvcResult result = mvc.perform(
                get("/message")).andReturn();


        final MockHttpServletResponse response = result.getResponse();
        assertEquals(200, response.getStatus());
    }



    @Test
    public void should_get_hello_message_all_case() throws Exception{
//        final MvcResult result = mvc.perform(get("/message")).andReturn();
//        final ObjectMapper objectMapper = new ObjectMapper();
//        final MessageResponse messageResponse = objectMapper.readValue(json, MessageResponse.class);
//        assertEquals("Hello",messageResponse.getMessage());

//        mvc.perform(get("/message")).andExpect(jsonPath("message"), is("Hello"));


        final String json = getJsonResponse("/message");

        final MessageResponse messageResponse = convertJsonToMessageResponse(json);

        assertEquals("Hello", messageResponse.getMessage());

    }


    private MessageResponse convertJsonToMessageResponse(String json) throws Exception{
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, MessageResponse.class);
    }

    private String getJsonResponse(String uri) throws  Exception{
        final MvcResult result = mvc.perform(get(uri)).andReturn();
        return result.getResponse().getContentAsString();
    }

    @Test
    public void should_get_hello_message() throws Exception {
        final String singleEntityName = "Hi";
        final SingleEntity entity = new SingleEntity();
        final long entityId = 2L;
        entity.id = entityId;
        entity.name = singleEntityName;
        singleEntityRepository.save(entity);
        singleEntityRepository.flush();

        final String json = getJsonResponse("/message");

        final MessageResponse messageResponse = convertJsonToMessageResponse(json);

        assertEquals(singleEntityName, messageResponse.getMessage());
    }
}