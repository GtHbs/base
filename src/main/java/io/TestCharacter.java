package io;

import java.io.*;

/**
 * @author 李昭
 */
public class TestCharacter {
    public static void main(String[] args) {
        testBuffered();
    }

    /**
     * 测试文件字符流输入
     */
    public static void testFileReader()
    {
        FileReader reader = null;
        int count = 0;
        try {
            reader = new FileReader("C:\\Users\\李昭\\Desktop\\sql.txt");
            int c;
            while ((c = reader.read()) != -1)
            {
                System.out.println((char)c);
                ++count;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                System.out.println("共传输了"+count+"个字符!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试文件字符流输出
     */
    public static void testFileWriter()
    {
        FileReader reader = null;
        FileWriter writer = null;
        int c;
        try {
            reader = new FileReader("C:\\Users\\李昭\\Desktop\\sql.txt");
            //文件不存在可以建文件,但是不能建目录
            writer = new FileWriter("C:\\Users\\李昭\\Desktop\\test02.txt");
            while ((c = reader.read()) != -1)
            {
                writer.write(c);
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testBuffered()
    {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String str;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\李昭\\Desktop\\test02.txt"));
            writer = new BufferedWriter(new FileWriter("C:\\Users\\李昭\\Desktop\\test01.txt"));
            while ((str = reader.readLine()) != null)
                writer.write(str);
            writer.newLine();       //写一个换行符
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
