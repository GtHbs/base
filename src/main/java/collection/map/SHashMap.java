package collection.map;

import java.util.Map;
import java.util.Objects;

public class SHashMap<K, V> {
    /**
     * hashMap节点
     *
     * @param <K>
     * @param <V>
     */
    class Node<K, V> implements Map.Entry<K, V> {

        private K key;
        private V val;
        private int hash;
        private Node<K, V> next;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return val;
        }

        @Override
        public V setValue(V value) {
            this.val = value;
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(getKey(), node.getKey()) &&
                    Objects.equals(val, node.val) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), val, hash, next);
        }
    }
    //哈希表
    private Node<K, V>[] table;
    //结点数量
    private int size;
    //默认大小为16
    private static final int INITILIZE_CAPACITY = 16;

    public SHashMap() {
        table = new Node[INITILIZE_CAPACITY];
    }

    public SHashMap(int capacity) {
        table = new Node[capacity];
    }

    public V put(K key, V val) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.val = val;
        node.hash = hash((Integer) node.key, table.length);
        node.next = null;
        Node temp = table[node.hash];
        if (temp == null) {
            table[node.hash] = node;
        } else {
            Node k = null;
            while (temp != null) {
                if (temp.key.equals(node.key)) {
                    Object value = temp.getValue();
                    temp.val = node.val;
                    val = (V) value;
                    break;
                } else {
                    k = temp;
                    temp = temp.next;
                }
            }
            if (k != null) {
                k.next = node;
                ++size;
            }
        }
        return val;
    }

    private int hash(int hash, int length) {
        return hash & (length - 1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        for (int i = 0; i < table.length; ++i)
        {
            Node temp = table[i];
            if (temp == null)
                continue;
            builder.append(i+":\t");
            while (temp != null)
            {
                builder.append("key: "+temp.key+" val:"+temp.val+"\t");
                temp = temp.next;
            }
            builder.append("\n");
        }
        builder.append("]");
        return builder.toString();
    }

    public V get(K key) {
        int hash = hash((Integer) key, table.length);
        Node node = table[hash];
        if (node == null)
            return null;
        else {
            while (node != null) {
                if (node.key.equals(key))
                    return (V) node.val;
                else
                    node = node.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        SHashMap<Integer, String> map = new SHashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        String c = map.put(1, "c");
        String s = map.get(2);
        System.out.println(s);
        System.out.println(map);
    }

}

