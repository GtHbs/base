package collection.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author 李昭
 */
public class TestSet {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(1);
    }
}
