package com.vitor.app.person;

import com.vitor.app.config.WebConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
@Import(WebConfig.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    @DisplayName("Should perform a get for list of persons")
    void scenario01() throws Exception {
        given(personService.findAll()).willReturn(List.of(new Person(), new Person()));
        this.mockMvc.perform(get("/person")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Should perform a get by id")
    void scenario02() throws Exception {
        given(personService.findById(anyLong())).willReturn(mock(Person.class));
        this.mockMvc.perform(get("/person/1")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().contains("name");
    }

}
