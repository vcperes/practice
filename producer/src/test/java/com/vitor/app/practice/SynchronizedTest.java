package com.vitor.app.practice;

import com.vitor.app.core.Speed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@EnableAspectJAutoProxy
class SynchronizedTest {

    @Test
    @DisplayName("Should sum synchronized method with int number")
    void test01() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(8);
        Synchronized sync = new Synchronized();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(sync::syncMethod));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, sync.getNum());
    }

    @Test
    @DisplayName("Should sum synchronized block with int number")
    void test02() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(8);
        Synchronized sync = new Synchronized();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(sync::syncBlock));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, sync.getNum());
    }

    @Test
    @DisplayName("Should sum synchronized static int number")
    void test03() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(8);

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(Synchronized::syncStatic));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, Synchronized.getNumStatic());
    }

    @Test
    @DisplayName("Should print a string concurrently")
    void test04() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000)
                        .forEach(count -> executor.submit(new Task()));
        executor.awaitTermination(100, TimeUnit.MILLISECONDS);
        assertThat(Task.class).isAssignableTo(Runnable.class);
    }
}
