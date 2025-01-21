package com.projects.learn.restful_clients.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.learn.restful_clients.model.Client;
import com.projects.learn.restful_clients.service.ClientService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ClientService clientService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @SneakyThrows
    void create() {
        // given
        Client client = new Client();
        client.setName("Test Client");
        client.setEmail("test@example.com");

        // when
        mockMvc.perform(post("/clients")
                .content(objectMapper.writeValueAsString(client))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // then
//        verify(clientService, times(1)).create(client);
    }

    @SneakyThrows
    @Test
    void readClientIsFound() {
        //given
        Client mockClient = new Client();
        mockClient.setId(1);
        mockClient.setName("Test Client");
        mockClient.setEmail("test@example.com");

        //when
        when(clientService.read(1)).thenReturn(mockClient);
        mockMvc.perform(get("/clients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockClient)));

        //then
        verify(clientService).read(1);
    }

    @SneakyThrows
    @Test
    void readClientIsNotFound() {
        //given
        when(clientService.read(1)).thenReturn(null);

        //when
        mockMvc.perform(get("/clients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        //then
        verify(clientService).read(1);
    }

    @SneakyThrows
    @Test
    void readAll() {
//        mockMvc.perform(get("/clients")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}