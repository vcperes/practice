package com.vitor.app.document;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Test
    @DisplayName("Should generate map for the source-code")
    void scenario01() throws IOException {
        Map<String, String> document = documentService.document();
        assertTrue(document.containsKey("DocumentService.java"));
    }
}
