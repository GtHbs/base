package thread;

import java.util.concurrent.*;

/**
 * @author 李昭
 */
public class Web12306 implements Runnable {

    private int ticketNums = 99;

    @Override
    public void run() {
        while (true) {
            if (ticketNums < 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +":"+ticketNums--);
        }
    }

    public static void main(String[] args) {
        new Thread(new Web12306(),"a").start();
        new Thread(new Web12306(),"b").start();
        new Thread(new Web12306(),"c").start();
    }
}
class Race implements Runnable {

    private String name;

    @Override
    public void run() {
        for (int i = 0; i <= 100; ++i) {
            if (Thread.currentThread().getName().equals("兔") && i % 10 == 0){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() +":"+i);
            boolean b = gameOver(i);
            if (b) {
                break;
            }
        }
    }
    public boolean gameOver(int step) {
        if (name != null) {
            return true;
        } else {
            if (step == 100) {
                System.out.println("winner"+Thread.currentThread().getName());
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race1 = new Race();
        Race race2 = new Race();
        new Thread(race1,"龟").start();
        new Thread(race2,"兔").start();
    }
}

class racer implements Callable<Boolean> {
    private String url;
    private String path;

    public racer(String url, String path) {
        this.url = url;
        this.path = path;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownLoad downLoad = new WebDownLoad();
        downLoad.downLoad(url, path);
        return true;
    }

    public static void main(String[] args) {
        racer racer01 = new racer("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%B5%9B%E5%88%A9%E4%BA%9A&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=4007950937,3492072978&os=4059540829,2657190957&simid=3167173958,3849157975&pn=1&rn=1&di=194150&ln=1879&fr=&fmq=1580284214547_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F8f7d6bb3afa7ef341e8d0f386a37bea8fdde459b.png",
                "C:\\Users\\李昭\\Desktop\\test\\a.jpg");
        racer racer02 = new racer("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%B5%9B%E5%88%A9%E4%BA%9A&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=4174567665,2016914206&os=2052531869,271788713&simid=4230671409,653156143&pn=5&rn=1&di=170830&ln=1879&fr=&fmq=1580284214547_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fp3.so.qhmsg.com%2Ft018abc8ddce2089568.jpg",
                "C:\\Users\\李昭\\Desktop\\test\\b.jpg");
        racer racer03 = new racer("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%B5%9B%E5%88%A9%E4%BA%9A&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1691026018,3934823066&os=2157363143,534541099&simid=4289943764,963391236&pn=14&rn=1&di=100430&ln=1879&fr=&fmq=1580284214547_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017c405ad56caba801204029f8a6a9.jpeg",
                "C:\\Users\\李昭\\Desktop\\test\\c.jpg");

        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Boolean> result01 = service.submit(racer01);
        Future<Boolean> result02 = service.submit(racer02);
        Future<Boolean> result03 = service.submit(racer03);
        try {
            Boolean r1 = result01.get();
            Boolean r2 = result02.get();
            Boolean r3 = result03.get();
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}