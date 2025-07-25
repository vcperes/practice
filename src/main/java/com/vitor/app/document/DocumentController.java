package com.vitor.app.document;

import com.vitor.app.core.Speed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    @Speed
    public String showCodeView(Model model) throws IOException {
        log.info("Retrieving full source-code from Practice Repository");

        Map<String, String> codeFiles = documentService.document();
        model.addAttribute("codeFiles", codeFiles);

        log.info("Source-code generated at view");
        return "code-viewer";
    }

}
