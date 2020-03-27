package thread;

/**
 * 生产者消费者问题
 *
 * @author 李昭
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        SyncStack syncStack = new SyncStack();
        Producer producer = new Producer(syncStack);
        Consumer consumer = new Consumer(syncStack);
        new Thread(consumer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
    }
}

/**
 * 馒头类
 */
class Bun {
    int id;

    Bun(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "";
    }
}

/**
 * 馒头筐类
 */
class SyncStack {
    int index = 0;
    Bun[] buns = new Bun[6];

    public synchronized void push(Bun bun) {
        while (index == buns.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        buns[index++] = bun;
    }

    public synchronized Bun pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();      //叫醒一个正在当前对象上的其他线程
        return buns[--index];
    }
}

class Producer implements Runnable {

    SyncStack syncStack = null;

    Producer(SyncStack stack) {
        super();
        this.syncStack = stack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 60; ++i) {
            Bun bun = new Bun(i);
            System.out.println("生产了:" + bun);
            syncStack.push(bun);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    SyncStack syncStack = null;

    Consumer(SyncStack stack) {
        this.syncStack = stack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            Bun bun = syncStack.pop();
            System.out.println("消费了:" + bun);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
