package optional;

import java.util.Optional;

/**
 * @author: 李昭
 * @Date: 3/24/2020 8:37 PM
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer value = null;
        Integer value2 = 2;
        Optional<Integer> a = Optional.ofNullable(value);
        System.out.println(a);
        Optional<Integer> b = Optional.of(value2);
        System.out.println(b);
        System.out.println(sum(a,b));
    }

    public static Integer sum(Optional<Integer> a,Optional<Integer> b) {
        System.out.println(a.isPresent());
        System.out.println(b.isPresent());
        Integer integer = a.orElse(new Integer(8));
        Integer l = b.get();
        return integer + l;
    }
}
