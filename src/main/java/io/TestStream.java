package io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 李昭
 */
public class TestStream {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\李昭\\Desktop\\test01.txt";
        if (fileName != null) {
            //list(System.out,fileName);
        }
    }

    /**
     * 测试文件输入流
     */
    @Test
    public void testFileInputStream() {
        int b = 0;
        InputStream stream = null;
        try {
            //流创建完后,就等于一个管道已经接入到文件上了
            stream = new FileInputStream("C:\\Users\\李昭\\Desktop\\test02.txt");
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件!!!");
            e.printStackTrace();
            //非正常退出
            System.exit(-1);
        }
        long num = 0;
        try {
            while ((b = stream.read()) != -1) {
                System.out.println((char) b);
                ++num;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误!!!");
        } finally {
            try {
                stream.close();
                System.out.println("共读取了" + num + "个字节!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试文件输出流
     */
    public static void testFileOutputStream() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int b = 0;
        try {
            inputStream = new FileInputStream("C:\\Users\\李昭\\Desktop\\test02.txt");
            //如果文件不存在,会自动创建,true表示在原文件的基础上进行添加
            outputStream = new FileOutputStream("C:\\Users\\李昭\\Desktop\\test01.txt", true);
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //输入流要在输出流之前关闭
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试缓冲流
     */
    public static void testBufferedStream() {
        FileInputStream inputStream = null;
        BufferedInputStream stream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\李昭\\Desktop\\test02.txt");
            /**
             * 缓冲流为字节流包装了一层
             * 第一个参数为字节流,第二个为缓冲区大小(20字节)
             */
            stream = new BufferedInputStream(inputStream, 20);
            int c = 0;
            System.out.println(stream.read());
            stream.mark(1);
            for (int i = 0; i < 10 && (c = stream.read()) != -1; ++i) {
                System.out.println((char) c + " ");
            }
            System.out.println();
            stream.reset();                     //返回到标记位
            for (int i = 0; i < 10 && (c = stream.read()) != -1; ++i) {
                System.out.println(c + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取的时候必须和写的顺序相同
     */
    public static void testDataStream() {
        //分配一个流指向一个字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //分配一个流向上面的字节数组中写入数据
        DataOutputStream dos = new DataOutputStream(outputStream);
        try {
            dos.writeDouble(Math.random());
            dos.writeBoolean(true);
            //分配一个字节数组,将上面写入的数据写到新的数组中
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            System.out.println(inputStream.available());
            //分配一个流从上面的数组中读取数据
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readBoolean());
            dos.close();
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testPrintStream() {
        PrintStream stream = null;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\李昭\\Desktop\\text02.txt");
            stream = new PrintStream(fos, true, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (stream != null)
                System.setOut(stream);      //设置新的打印流
            int ln = 0;
            for (char c = 0; c <= 600; ++c) {
                System.out.print(c + " ");
                if (ln++ >= 100) {
                    System.out.println();
                    ln = 0;
                }
            }
        }
    }

    /**
     * 打印文件内容
     *
     * @param stream
     * @param fileName
     */
    public static void list(PrintStream stream, String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String s = null;
            while ((s = reader.readLine()) != null) {
                stream.print(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testPrintStream02() {
        String s = null;
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new FileWriter("C:\\Users\\李昭\\Desktop\\test.log", true);
            PrintWriter printWriter = new PrintWriter(writer);
            while ((s = reader.readLine()) != null) {
                if (s.equalsIgnoreCase("exit"))
                    break;
                System.out.println(s.toUpperCase());
                printWriter.print("------------\n");
                printWriter.print(s.toUpperCase());
                printWriter.flush();
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
