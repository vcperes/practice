package com.vitor.app.person;

import com.vitor.app.core.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person, Long> {}
