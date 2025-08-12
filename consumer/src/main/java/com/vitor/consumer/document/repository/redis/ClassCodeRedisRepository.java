package com.vitor.consumer.document.repository.redis;

import com.vitor.consumer.document.ClassCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassCodeRedisRepository extends CrudRepository<ClassCode, String> {
}
