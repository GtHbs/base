package io.commonsIo;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author 李昭
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        copy();
    }

    public static void size() {
        //可以获取文件和文件夹大小
        long size = FileUtils.sizeOf(new File("C:\\Users\\李昭\\Desktop\\sql.txt"));
        long size2 = FileUtils.sizeOf(new File("C:\\Users\\李昭\\Desktop"));
        System.out.println(size);
        System.out.println(size2);
    }

    public static void list() {
        //参数:第一个为文件,第二个为操作文件参数,第三个为操作方式可以为当前级也可以为子孙级
        Collection<File> files = FileUtils.listFiles(new File("C:\\Users\\李昭\\Desktop"),
                EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
        Collection<File> files2 = FileUtils.listFiles(new File("C:\\Users\\李昭\\Desktop"),
                FileFilterUtils.or(new SuffixFileFilter(".java"),new SuffixFileFilter(".class")), DirectoryFileFilter.INSTANCE);
        Iterator<File> iterator = files2.iterator();
        while (iterator.hasNext()) {
            File file = iterator.next();
            System.out.println(file.getAbsolutePath());
        }
    }

    public static void read() throws IOException {
        //直接读取文件内容到一个字符串
        String string = FileUtils.readFileToString(new File("C:\\Users\\李昭\\Desktop\\sql.txt"),
                "GBK");
        List<String> list = FileUtils.readLines(new File("C:\\Users\\李昭\\Desktop\\sql.txt"), "GBK");
        for (String s : list) {
            System.out.println(s);
        }
        //System.out.println(string);
    }
    public static void write() throws IOException {
        String string = FileUtils.readFileToString(new File("C:\\Users\\李昭\\Desktop\\sql.txt"),
                "GBK");
        FileUtils.write(new File("C:\\Users\\李昭\\Desktop\\sql2.txt"),string,"GBK",true);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        FileUtils.writeLines(new File("C:\\Users\\李昭\\Desktop\\sql3.txt"),list,"...",true);
    }
    public static void copy() throws IOException {
        //复制文件
        //FileUtils.copyFile(new File("C:\\Users\\李昭\\Desktop\\sql.txt"),
          //      new File("C:\\Users\\李昭\\Desktop\\sql4.txt"));
        //复制文件到目录
        //FileUtils.copyFileToDirectory(new File("C:\\Users\\李昭\\Desktop\\sql.txt"),
          //      new File("C:\\Users\\李昭\\Desktop\\TreeMapSourceAnalysis-master"));
        //复制目录到目录
        //FileUtils.copyDirectoryToDirectory(new File("C:\\Users\\李昭\\Desktop\\commons-io-2.6"),
          //      new File("C:\\Users\\李昭\\Desktop\\TreeMapSourceAnalysis-master"));
        //FileUtils.copyURLToFile(new URL("file:///C:/Users/%E6%9D%8E%E6%98%AD/Desktop/commons-io-2.6/docs/index.html"),
          //      new File("C:\\Users\\李昭\\Desktop\\TreeMapSourceAnalysis-master\\x.html"));
        String string = IOUtils.toString(new URL("http://www.baidu.com"), "UTF-8");
        System.out.println(string);
    }
}
