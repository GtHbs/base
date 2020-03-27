package thread.synchronize;

public class ConCurrent {
    public static void main(String[] args) {
        TV tv = new TV();
        Actor actor = new Actor(tv);
        Audience audience = new Audience(tv);
        actor.start();
        audience.start();
    }
}
class Actor extends Thread {
    private TV tv;

    public Actor(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            if (i % 2 == 0) {
                this.tv.show("kkk");
            } else {
                this.tv.show("xxx");
            }
        }
    }
}
class Audience extends Thread {
    private TV tv;

    public Audience(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        tv.watch();
    }
}

class TV {
    String voice;
    boolean flag = true;

    public synchronized void show(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {}
        }
        this.voice = voice;
        System.out.println("表演了:"+voice);
        this.flag = !this.flag;
        this.notifyAll();

    }
    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {}
        }
        this.notifyAll();
        System.out.println("听到了:"+voice);
    }
}