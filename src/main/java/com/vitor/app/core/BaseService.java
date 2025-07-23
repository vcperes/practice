package com.vitor.app.core;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseService<T> implements IBaseService<T> {

    protected abstract BaseRepository<T, Long> getRepository();

    @Override
    public T findById(Long id) {
        return getRepository().findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Page<T> findAllPaginated(int page, int size) {
        return getRepository().findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    @Transactional
    public void remove(T t) {
        getRepository().delete(t);
    }

    @Override
    public Long count() {
        return getRepository().count();
    }
}