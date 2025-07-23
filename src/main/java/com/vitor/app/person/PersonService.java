package com.vitor.app.person;

import com.vitor.app.core.BaseRepository;
import com.vitor.app.core.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseService<Person> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    protected BaseRepository<Person, Long> getRepository() {
        return personRepository;
    }
}
