package com.vitor.app.core;

import com.vitor.app.person.Person;
import com.vitor.app.person.PersonRepository;
import com.vitor.app.person.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaseServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private Person person;

    @Test
    @DisplayName("Should find by id")
    void scenario01(){
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));
        assertEquals(person, personService.findById(1L));
    }

    @Test
    @DisplayName("Should find all persons")
    void scenario02(){
        given(personRepository.findAll()).willReturn(List.of(person));
        assertEquals(List.of(person), personService.findAll());
    }

    @Test
    @DisplayName("Should find all paginated")
    void scenario03(){
        Page<Person> page = new PageImpl<>(List.of(person));
        given(personRepository.findAll(any(PageRequest.class))).willReturn(page);
        assertEquals(page, personService.findAllPaginated(0, 10));
    }

    @Test
    @DisplayName("Should save a new Person")
    void scenario04(){
        personService.save(new Person());
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    @DisplayName("Should remove a person")
    void scenario05(){
        personService.remove(new Person());
        verify(personRepository, times(1)).delete(any(Person.class));
    }

    @Test
    @DisplayName("Should count persons")
    void scenario06(){
        given(personRepository.count()).willReturn(10L);
        assertEquals(10L, personService.count());
    }
}
