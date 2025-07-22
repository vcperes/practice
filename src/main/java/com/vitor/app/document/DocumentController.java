package com.vitor.app.document;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public String showCodeView(Model model) throws IOException {
        log.info("Retrieving full source-code from Practice Repository");

        Map<String, String> codeFiles = documentService.document();
        model.addAttribute("codeFiles", codeFiles);

        log.info("Source-code generated at view");
        return "code-viewer";
    }
}
