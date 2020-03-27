package collection.list;

/**
 * 实现LinkedList(非带头结点的双链表)
 *
 * @author 李昭
 */
public class LinkedList<E> {
    /**
     * 节点对象
     *
     * @param <E>
     */
    class Node<E> {
        private Node next;
        private E data;
        private Node pre;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }

        public Node(Node next, E data, Node pre) {
            this.next = next;
            this.data = data;
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public LinkedList() {
    }

    public int getSize() {
        return size;
    }

    /**
     * 添加节点,非带头结点,尾插法
     *
     * @param e
     */
    public void add(E e) {
        Node node = new Node(e);
        if (first == null) {
            first = last = node;
            node.next = node.pre = null;
        } else {
            node.next = null;
            node.pre = last;
            last.next = node;
            last = node;
        }
        ++size;
    }

    /**
     * 添加一个链表
     *
     * @param list
     */
    public void addAll(LinkedList<E> list) {
        if (first == null) {
            first = list.first;
            last = list.last;
            size = list.size;
        } else {
            list.first.pre = last;
            last.next = list.first;
            last = list.last;
            size += list.size;
        }
    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (!checkRange(index))
            throw new RuntimeException("下标错误");
        Node temp = first;
        int i = 0;
        while (i < index) {
            temp = temp.next;
            ++i;
        }
        return (E) temp.data;
    }

    /**
     * 删除节点
     *
     * @param index
     * @return
     */
    public boolean remove(int index) {
        if (!checkRange(index))
            throw new RuntimeException("下标错误");
        else {
            if (index == 0) {
                Node temp = first;
                first = first.next;
                --size;
                return true;
            } else {
                Node pre = first;
                int i = 1;
                while (pre.next != null) {
                    if (i == index)
                        break;
                    else {
                        pre = pre.next;
                        ++i;
                    }
                }
                Node node = pre.next;
                pre.next = node.next;
                node.next.pre = pre;
                --size;
                return true;
            }
        }
    }

    /**
     * 删除具体元素
     *
     * @param element
     * @return
     */
    public boolean remove(E element) {
        if (first == null)
            throw new RuntimeException("链表为空");
        else {
            if (first.equals(element)) {
                first = first.next;
                --size;
                return true;
            } else {
                Node pre = first;
                while (pre.next != null) {
                    if (pre.next.data.equals(element))
                        break;
                    else {
                        pre = pre.next;
                    }
                }
                Node temp = pre.next;
                pre.next = temp.next;
                temp.next.pre = pre;
                --size;
                return true;
            }
        }
    }

    /**
     * 修改元素
     *
     * @param index
     * @param element
     * @return
     */
    public boolean update(int index, E element) {
        if (!checkRange(index))
            throw new RuntimeException("下标错误");
        else {
            if (index == 0) {
                first.data = element;
                return true;
            } else {
                Node temp = first;
                int i = 0;
                while (i < index) {
                    temp = temp.next;
                    ++i;
                }
                temp.data = element;
                return true;
            }
        }
    }

    /**
     * 插入节点
     *
     * @param index
     * @param element
     * @return
     */
    public boolean insert(int index, E element) {
        if (!checkRange(index))
            throw new RuntimeException("下标错误");
        else {
            Node node = new Node(element);
            if (index == 0) {
                first.pre = node;
                node.next = first;
                first = node;
                ++size;
                return true;
            } else {
                Node pre = first;
                int i = 1;
                while (i < index) {
                    ++i;
                    pre = pre.next;
                }
                Node temp = pre.next;
                pre.next = node;
                node.pre = pre;
                node.next = temp;
                temp.pre = node;
                ++size;
                return true;
            }
        }
    }


    /**
     * 判断下标是否正确
     *
     * @param index
     * @return
     */
    public boolean checkRange(int index) {
        if (index < 0 || index > size)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node temp = first;
        while (temp != null) {
            builder.append(temp.data + ",");
            temp = temp.next;
        }
        builder.append("size=" + size);
        builder.append("]");
        return builder.toString();
    }
}
