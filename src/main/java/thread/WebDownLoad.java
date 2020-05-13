package thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李昭
 */
public class WebDownLoad {
    public void downLoad(String url, String path) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(path));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("不合法的路径");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }
    }
    private static final Integer K = 1024;

    public static void main(String[] args) {
        int size = K * K * 8;
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            System.out.println("JVM 写入数据"+(i + 1) +"M");
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(new byte[size]);
    }

}


class DownLoad extends Thread {
    private String url;
    private String path;

    DownLoad(String url, String path) {
        this.url = url;
        this.path = path;
    }

    @Override
    public void run() {
        WebDownLoad downLoad = new WebDownLoad();
        downLoad.downLoad(url, path);
    }

    public static void main(String[] args) {
        DownLoad downLoad01 = new DownLoad("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%B5%9B%E5%88%A9%E4%BA%9A&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=4007950937,3492072978&os=4059540829,2657190957&simid=3167173958,3849157975&pn=1&rn=1&di=194150&ln=1879&fr=&fmq=1580284214547_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F8f7d6bb3afa7ef341e8d0f386a37bea8fdde459b.png",
                "C:\\Users\\李昭\\Desktop\\test\\a.jpg");
        DownLoad downLoad02 = new DownLoad("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%B5%9B%E5%88%A9%E4%BA%9A&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=4174567665,2016914206&os=2052531869,271788713&simid=4230671409,653156143&pn=5&rn=1&di=170830&ln=1879&fr=&fmq=1580284214547_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fp3.so.qhmsg.com%2Ft018abc8ddce2089568.jpg",
                "C:\\Users\\李昭\\Desktop\\test\\b.jpg");
        DownLoad downLoad03 = new DownLoad("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%B5%9B%E5%88%A9%E4%BA%9A&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1691026018,3934823066&os=2157363143,534541099&simid=4289943764,963391236&pn=14&rn=1&di=100430&ln=1879&fr=&fmq=1580284214547_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017c405ad56caba801204029f8a6a9.jpeg",
                "C:\\Users\\李昭\\Desktop\\test\\c.jpg");
        downLoad01.start();
        downLoad02.start();
        downLoad03.start();
    }
}
