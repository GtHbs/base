package collection.list;

/**
 * 实现ArrayList
 *
 * @author 李昭
 */
public class ArrayList<E> {
    private static final int INITIALIZE_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    /**
     * 默认初始化
     */
    public ArrayList() {
        elementData = new Object[INITIALIZE_CAPACITY];
    }

    public int getSize() {
        return size;
    }

    public E get(int index) {
        if (!ensureCapacity(index))
            throw new IndexOutOfBoundsException("下标越界");
        return (E) elementData[index];
    }

    public boolean set(int index, E element) {
        if (!ensureCapacity(index)) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        elementData[index] = element;
        return true;
    }

    /**
     * 指定固定长度
     *
     * @param capacity
     */
    public ArrayList(int capacity) {
        if (capacity < 0)
            throw new RuntimeException("容量不能为负");
        else if (capacity == 0)
            elementData = new Object[INITIALIZE_CAPACITY];
        else
            elementData = new Object[capacity];
    }

    /**
     * 添加单个元素
     *
     * @param obj
     * @return
     */
    public boolean add(E obj) {
        if (elementData.length == size)
            grow();
        if (elementData.length < size / 2)
            shrink();
        elementData[size++] = obj;
        return true;
    }

    /**
     * 添加一个对象
     *
     * @param lists
     * @return
     */
    public boolean addAll(ArrayList<E> lists) {
        for (int i = 0; i < lists.size; ++i)
            this.add((E) lists.get(i));
        return true;
    }

    /**
     * 判断下标
     *
     * @param index
     * @return
     */
    private boolean ensureCapacity(int index) {
        if (index >= size || index < 0)
            return false;
        return true;
    }

    /**
     * 按照下标删除元素
     *
     * @param index
     * @return
     */
    public boolean remove(int index) {
        if (!ensureCapacity(index)) {
            throw new IndexOutOfBoundsException("");
        }
        System.arraycopy(elementData,index + 1,elementData,index,size - index);
        elementData[size--] = null;
        if (size < elementData.length / 2)
            shrink();
        return true;
    }

    /**
     * 删除具体元素
     *
     * @param element
     * @return
     */
    public boolean remove(E element) {
        int i;
        for (i = 0; i < size; ++i)
            if (elementData[i].equals(element))
                break;
        if (i == size)
            throw new RuntimeException("元素不存在");
        else {
            remove(i);
        }
        return true;
    }

    /**
     * 按照容器删除
     *
     * @param list
     * @return
     */
    public boolean removeAll(ArrayList<E> list) {
        E temp;
        for (int i = 0; i < list.size; ++i) {
            int j = 0;
            temp = list.get(i);
            for (; j < this.size; ++j)
                if (elementData[j].equals(temp))
                    break;
            if (j < size) {
                remove(j);
            }
        }
        return true;
    }

    /**
     * 判断元素是否存在
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        for (Object o : elementData)
            if (o.equals(element))
                return true;
        return false;
    }

    /**
     * 数组扩容函数,每次扩容一半
     */
    private final void grow() {
        int length = elementData.length;
        int x = length == 1 ? 1 : length;
        int newLength = x + length;
        Object[] newArr = new Object[newLength];
        System.arraycopy(elementData, 0, newArr, 0, length);
        elementData = newArr;
    }

    /**
     * 数组缩容函数,每次缩容一半
     */
    private final void shrink() {
        int newLength = elementData.length >> 1;
        Object[] newArr = new Object[newLength];
        System.arraycopy(elementData, 0, newArr, 0, size);
        elementData = newArr;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Object o : elementData) {
            if (o != null)
                builder.append(o.toString() + ",");
        }
        builder.append("size=" + size);
        builder.append("]");
        return builder.toString();
    }

}
