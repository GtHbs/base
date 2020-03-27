package redis;

import java.util.BitSet;

/**
 * 实现布隆过滤器
 *
 * @author: 李昭
 * @Date: 2020/3/27 16:52
 */
public class BloomFilter {
    /**
     * 静态内部类。用于 hash 操作！
     */
    private static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }

    /**
     * 默认大小
     */
    private static final Integer DEFAULT_ZIZE = 2 << 24;

    /**
     * 创建6个hash函数
     */
    private static final Integer[] SEEDS = {3, 13, 46, 71, 91, 134};

    /**
     * 布隆过滤器只能是1和0两种数组
     */
    private BitSet bits = new BitSet(DEFAULT_ZIZE);

    /**
     * 存放多个hash函数的类的数组
     */
    private SimpleHash[] hashes = new SimpleHash[SEEDS.length];

    public BloomFilter() {
        for (int i = 0; i < SEEDS.length; ++i) {
            hashes[i] = new SimpleHash(DEFAULT_ZIZE, SEEDS[i]);
        }
    }


    public void add(Object value) {
        for (SimpleHash hash : hashes) {
            bits.set(hash.hash(value),true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : hashes) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

}


