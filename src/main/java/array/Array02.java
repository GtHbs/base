package array;

import java.util.Arrays;

/**
 * @auther
 */
public class Array02 {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,-1};
        String str[] = {"A","B"};
        System.out.println(Arrays.toString(arr));       //将数组元素拼接成一个字符串
        Arrays.sort(arr);               //底层使用快排实现
        System.out.println(Arrays.toString(arr));
        //testBasicCopy();

        long current = Runtime.getRuntime().freeMemory(); //当前主存空间
        long millis = System.currentTimeMillis();         //当前时间戳

    }

    /**
     * 使用本地方法进行数组拷贝(使用c++实现,因此效率比较高)
     * 也可以使用该方法实现数组的删除和插入
     */
    public static void testBasicCopy()
    {
        String st1[] = {"A","B","c","D"};
        String str2[] = new String[10];
        /**
         * 参数列表:第一个为复制的数组,第二个为复制数组的起始位置,第三个参数为新数组,第四个为
         * 新数组的复制位置,第五个为复制的长度
         */
        System.arraycopy(st1,1,str2,4,2);
        System.out.println("copy new");
        for (String str : str2)
        {
            System.out.println(str);
        }
        System.out.println("-------------------------------------");
        removeElement(st1,2);
        System.out.println("-------------------------------------");
        insertElement(st1,2,"X");
    }
    public static String[] removeElement(String str[],int index)
    {
        System.arraycopy(str,index + 1,str,index,str.length - index - 1);
        str[str.length - 1] = null;
        for (String string : str)
            System.out.println(string);
        return str;
    }

    /**
     * 插入元素
     * @param str
     * @param index
     * @param elem
     * @return
     */
    public static String[] insertElement(String[] str,int index,String elem)
    {
        String[] strings = new String[str.length + 1];
        System.arraycopy(str,0,strings,0,str.length);
        System.arraycopy(strings,index,strings,index + 1,str.length - index);
        strings[index] = elem;
        for (String string : strings)
            System.out.println(string);
        return strings;
    }



}
