package com.vitor.app.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Person>> list(){
        log.info("Listing all the persons at H2 database");
        log.info("Found {} persons", personService.count());
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> findById(@PathVariable("id") Long id){
        log.info("Finding person by id {}", id);
        return ResponseEntity.ok(personService.findById(id));
    }
}
