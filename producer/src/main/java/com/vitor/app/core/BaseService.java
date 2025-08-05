package com.vitor.app.core;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@FunctionalInterface
public interface BaseService<T> {

    BaseRepository<T, Long> getRepository();

    default T findById(Long id) {
        return getRepository().findById(id).orElseThrow(EntityNotFoundException::new);
    }

    default List<T> findAll() {
        return getRepository().findAll();
    }

    default Page<T> findAllPaginated(int page, int size) {
        return getRepository().findAll(PageRequest.of(page, size));
    }

    @Transactional
    default T save(T t) {
        return getRepository().save(t);
    }

    @Transactional
    default void remove(T t) {
        getRepository().delete(t);
    }

    default Long count() {
        return getRepository().count();
    }
}