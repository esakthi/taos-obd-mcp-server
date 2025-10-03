package com.taos.obd.mcp.tool;

import com.taos.obd.mcp.dto.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.graphql.client.GraphQlClient;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LaunchToolTest {

    @Mock
    private GraphQlClient graphQlClient;

    @Mock
    private GraphQlClient.RequestSpec requestSpec;

    @InjectMocks
    private LaunchTool launchTool;

    @Captor
    private ArgumentCaptor<Map<String, Object>> variablesCaptor;

    @Test
    void getAllLaunches_withOrder_shouldPassCorrectOrderValue() {
        // Given
        when(graphQlClient.document(anyString())).thenReturn(requestSpec);
        when(requestSpec.variables(variablesCaptor.capture())).thenReturn(requestSpec);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            launchTool.getAllLaunches(10, 0, "launch_date_local", Order.ASC);
        });

        Map<String, Object> capturedVariables = variablesCaptor.getValue();
        assertEquals("ASC", capturedVariables.get("order"));
    }

    @Test
    void getAllLaunches_withNullOrder_shouldNotAddOrderToVariables() {
        // Given
        when(graphQlClient.document(anyString())).thenReturn(requestSpec);
        when(requestSpec.variables(variablesCaptor.capture())).thenReturn(requestSpec);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            launchTool.getAllLaunches(10, 0, "launch_date_local", null);
        });

        Map<String, Object> capturedVariables = variablesCaptor.getValue();
        assertFalse(capturedVariables.containsKey("order"));
    }
}