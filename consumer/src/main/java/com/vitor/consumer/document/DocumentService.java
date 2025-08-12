package com.vitor.consumer.document;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.vitor.consumer.document.repository.mongo.ClassCodeMongoRepository;
import com.vitor.consumer.document.repository.redis.ClassCodeRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final ClassCodeMongoRepository mongoRepo;
    private final ClassCodeRedisRepository redisRepo;

    public ClassCode parseCode(Map.Entry<String, String> entry){
            String className = entry.getKey();
            log.info("Compiling for class {}", className);
            String code = entry.getValue();

            CompilationUnit cu = StaticJavaParser.parse(code);
            ClassCode classCode = new ClassCode(className, new ArrayList<>());
            log.info("Object for class {} created", className);

            cu.findAll(ClassOrInterfaceDeclaration.class).forEach(clazz -> clazz.getMethods().forEach(method -> {
                String signature = method.getDeclarationAsString(true, true, true);
                log.info("Signature {} for class {} created", signature, className);
                classCode.getMethods().add(signature);
            }));

            return classCode;
    }

    public void save(ClassCode classCode){
        ClassCode saved = mongoRepo.save(classCode);
        redisRepo.save(saved);
    }


    @Cacheable(value = "classcodes", unless="#result == null")
    public List<ClassCode> findAll(){
        return mongoRepo.findAll().stream().map(c -> {
            redisRepo.save(c);
            return c;
        }).toList();
    }

}
