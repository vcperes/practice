package com.vitor.app.person;

import com.vitor.app.core.BaseRepository;
import com.vitor.app.core.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService extends BaseService<Person> {

    private final PersonRepository personRepository;

    @Override
    protected BaseRepository<Person, Long> getRepository() {
        return personRepository;
    }
}
