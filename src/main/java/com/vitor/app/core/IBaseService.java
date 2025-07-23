package com.vitor.app.core;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T> {
    T findById(Long id);
    List<T> findAll();
    Page<T> findAllPaginated(int page, int size);
    T save(T t);
    void remove(T t);
    Long count();
}
