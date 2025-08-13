package com.vitor.consumer.classcode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ClassCodeController {
    private final ClassCodeService classCodeService;

    @GetMapping("/code")
    public String showProject(Model model) {
        log.info("Trying to find all classes from Redis");
        Iterable<ClassCode> classes = classCodeService.findAll();

        log.info("Found {} classes!", classes.spliterator().getExactSizeIfKnown());
        model.addAttribute("classes", classes);
        return "code-view";
    }
}
