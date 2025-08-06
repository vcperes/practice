package com.vitor.app.document;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DocumentService documentService;

    @MockitoBean
    private DocumentProducer documentProducer;

    @Test
    @DisplayName("Should perform a get for json string of full source-code")
    void scenario01() throws Exception {
        Map<String, String> mockCodeFiles = Map.of("DocumentController.java", "code");
        given(documentService.document()).willReturn(mockCodeFiles);
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("codeFiles", mockCodeFiles))
                .andExpect(content().string(containsString("DocumentController.java")));
        verify(documentProducer).sendMessage(mockCodeFiles);
    }

}
