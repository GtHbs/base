package juc.threadpool;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Author: 李昭
 * @Date: 2020/3/20 19:03
 */
public class TestForkJoinPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinDemo demo = new ForkJoinDemo(0L, 10000000000L);
        Instant start = Instant.now();
        ForkJoinTask<Long> submit = pool.submit(demo);
        Long aLong = submit.get();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(aLong);
    }

    @Test
    public void test() {
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i < 10000000000L; ++i) {
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(sum);
    }

    @Test
    public void test8() {
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0L, 10000000000L).parallel().reduce(0L, Long::sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
}

class ForkJoinDemo extends RecursiveTask<Long> {

    private static final long serialVersionUID = -5111377438642848542L;

    private long start;
    private long end;

    /**
     * 临界值
     */
    private static final long THRESHOLD = 10000L;

    public ForkJoinDemo(final long start, final long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i < end; ++i) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinDemo left = new ForkJoinDemo(start, middle);
            left.fork();    //进行拆分
            ForkJoinDemo right = new ForkJoinDemo(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
