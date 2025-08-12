package com.vitor.consumer.document;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassCodeController {
    private final DocumentService documentService;

    @GetMapping("/code")
    public String showProject(Model model) {
        Iterable<ClassCode> classes = documentService.findAll();
        model.addAttribute("classes", classes);
        return "codigo-view";
    }
}
