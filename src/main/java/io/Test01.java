package io;

import java.io.*;

/**
 * @author 李昭
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        testOutputStream2();
    }
    public static void test01()
    {
        //缓冲字符流
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            char c = 0;
            String str;
            do {
                //c = (char)reader.read();        //读取字符
                str = reader.readLine();          //读取字符串
                System.out.println(str);
            }while (!str.toLowerCase().equals("quit"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void write()
    {
        int b;
        b = 'A';
        System.out.write(b);    //类似于System.out.println();
        System.out.write('\n');
    }
    public static void testFileInputStream()
    {
        InputStream input = null;
        try {
            File file = new File("C:/Users/李昭/Desktop/sql.txt");
            input = new FileInputStream(file);
            char word = (char) input.read();
            System.out.println(word);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void testByteArrayInputStream() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(20);
        while (stream.size() != 10)
        {
            stream.write(System.in.read());             //从键盘读入
        }
        byte[] bytes = stream.toByteArray();            //键盘读入数据存储到字节数组中
        System.out.println("Print the content!!");
        for (int i = 0; i < bytes.length; ++i)
        {
            System.out.print((char) bytes[i] + " ");
        }
        System.out.println(" ");
        int c;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        System.out.println("Converting characters to Upper case ");
        for (int i = 0; i < 1; ++i)
        {
            while ((c  = inputStream.read()) != -1)
            {
                System.out.print(Character.toUpperCase((char)c));
            }
        }
        inputStream.reset();
    }

    public static void testDataInputStream()
    {
        DataInputStream stream = null;
        DataOutputStream outputStream = null;
        try {
            stream = new DataInputStream(
                    new FileInputStream(new File("C:/Users/李昭/Desktop/sql.txt")));
            outputStream = new DataOutputStream(
                    new FileOutputStream(new File("C:/Users/李昭/Desktop/sqls.txt")));
            String count;
            while ((count = stream.readLine()) != null)
            {
                String s = count.toUpperCase();
                System.out.println(s);
                outputStream.writeBytes(s +",");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testInputStream()
    {
        InputStream stream = null;
        try {
            stream = new FileInputStream("C:/Users/李昭/Desktop/sqls.txt");
            int size = stream.available();
            for (int i = 0; i < size; ++i)
            {
                System.out.println((char)stream.read() + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testOutputStream()
    {
        OutputStream stream = null;
        try {
            byte[] bytes = {'a','b','c','d','e','f','g'};
            //写入时可能会存在乱码问题
            stream = new FileOutputStream("C:/Users/李昭/Desktop/sqls.txt");
            for (int i = 0; i < bytes.length; ++i)
            {
                stream.write(bytes[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void testOutputStream2()
    {
        File file = new File("C:/Users/李昭/Desktop/sqls.txt");
        OutputStream stream = null;
        InputStream inputStream = null;
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        try {
            stream = new FileOutputStream(file);
            writer = new OutputStreamWriter(stream,"UTF-8");
            writer.append("中文输入!!");
            writer.append("\r\n");
            writer.append("English");
            writer.flush();
            System.out.println("--------------------------------------------");
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream,"UTF-8");
            StringBuffer buffer = new StringBuffer();
            while (reader.ready())
            {
                buffer.append((char)reader.read());
            }
            System.out.println(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                reader.close();
                stream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
