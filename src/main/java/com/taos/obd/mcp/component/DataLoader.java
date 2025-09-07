package com.taos.obd.mcp.component;

import com.taos.obd.mcp.service.RagService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RagService ragService;

    public DataLoader(RagService ragService) {
        this.ragService = ragService;
    }

    @Override
    public void run(String... args) throws Exception {
        ragService.load();
    }
}
