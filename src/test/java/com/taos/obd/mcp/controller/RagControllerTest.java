package com.taos.obd.mcp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatClient chatClient;

    @Test
    void rag() throws Exception {
        String expectedResponse = "This is a test response.";
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedResponse)));
        when(chatClient.call(any(org.springframework.ai.chat.prompt.Prompt.class))).thenReturn(chatResponse);

        mockMvc.perform(get("/api/v1/rag?query=test"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
