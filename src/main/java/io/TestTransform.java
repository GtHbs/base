package io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 转换流
 *
 * @author 李昭
 */
public class TestTransform {
    public static void main(String[] args) {

    }

    /**
     * 输出字节流转换为字符流
     */
    @Test
    public void testOutputStreamToWriter() {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(
                    new FileOutputStream("C:\\Users\\李昭\\Desktop\\test02.txt", true),
                    "UTF-8");
            writer.write("邓思萱");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testInputStreamToReader() {
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        String str;
        try {
            /**
             * 三层:
             *  第一层:System.in为字节流
             *  第二层:InputStreamReader为字符流
             *  第三曾:BufferedReader为缓冲字符流
             *  转换步骤:字节流->字符流->缓冲字符流
             *  关闭时只需要关闭最外面一层即可
             */
            reader = new InputStreamReader(System.in, "UTF-8");
            bufferedReader = new BufferedReader(reader);
            str = bufferedReader.readLine();
            while (str != null) {
                if (str.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println(str.toUpperCase());
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() {
        try {
            FileOutputStream outputStream = new FileOutputStream(
                    "C:\\Users\\李昭\\Desktop\\test02.txt", true);
            byte[] bytes = String.valueOf(10000).getBytes();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
