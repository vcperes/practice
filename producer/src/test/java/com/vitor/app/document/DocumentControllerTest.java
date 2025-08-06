package com.vitor.app.document;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vitor.app.config.WebConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Map;


@WebMvcTest(DocumentController.class)
@Import(WebConfig.class)
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
