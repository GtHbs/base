package streamclass;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author: 李昭
 * @Date: 3/24/2020 7:58 PM
 */
public class GeneratorStreamTest {

    @Test
    public void testGeneratorStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.stream().filter(i -> i > 4).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void testForeach() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    @Test
    public void testMap() {
        List<Integer> list = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> collect = list.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void testSorted() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    @Test
    public void testParallel() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "");
        long count = list.parallelStream().filter(String::isEmpty).count();
        System.out.println(count);
    }


    @Test
    public void testCollectors() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> collect = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        collect.forEach(System.out::println);
        //合并字符串
        String s = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println(s);
    }

    @Test
    public void testStatistic() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics statistics = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Max:" + statistics.getMax());
        System.out.println("Min:" + statistics.getMin());
        System.out.println("Sum:" + statistics.getSum());
        System.out.println("Avg:" + statistics.getAverage());
    }


}

