package thread.synchronize;

/**
 * 消费者生产者实现方式1:管程法
 *
 * @author 李昭
 */
public class Cooperation {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        producer.start();
        consumer.start();
    }
}

//生产者
class Producer extends Thread {

    Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            buffer.push(new Bun(i));
            System.out.println("生产第" + i + "个馒头!!!");
        }
    }
}

//消费者
class Consumer extends Thread {
    Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println("消费第" + buffer.pop().id + "个馒头");
        }
    }
}

//缓冲区
class Buffer {
    Bun[] buns = new Bun[10];
    int count = 0;

    //存取
    public synchronized void push(Bun bun) {
        if (count == buns.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        buns[count++] = bun;
        this.notifyAll();
    }

    public synchronized Bun pop() {
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        Bun bun = buns[--count];
        this.notifyAll();
        return bun;
    }
}

//馒头
class Bun {
    int id;

    public Bun(int id) {
        this.id = id;
    }
}