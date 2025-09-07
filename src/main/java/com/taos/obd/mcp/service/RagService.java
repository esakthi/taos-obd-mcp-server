package com.taos.obd.mcp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RagService {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    @Value("classpath:spacex_data.json")
    private Resource spacexData;

    @Value("classpath:rag-prompt-template.st")
    private Resource ragPromptTemplate;

    public RagService(VectorStore vectorStore, ChatClient chatClient) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
    }

    public void load() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> data = objectMapper.readValue(spacexData.getInputStream(), new TypeReference<>() {
        });

        List<Document> documents = data.stream()
                .map(entry -> {
                    String content = entry.entrySet().stream()
                            .map(e -> e.getKey() + ": " + e.getValue())
                            .collect(Collectors.joining("\n"));
                    return new Document(content, entry);
                })
                .collect(Collectors.toList());

        vectorStore.add(documents);
    }

    public String query(String query) {
        List<Document> similarDocuments = vectorStore.similaritySearch(query);
        String documents = similarDocuments.stream()
                .map(Document::getContent)
                .collect(Collectors.joining("\n"));

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(ragPromptTemplate);
        Prompt prompt = systemPromptTemplate.create(Map.of("documents", documents, "query", query));

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}
