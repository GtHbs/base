package objectoriented.ooproject.util;

import java.util.Scanner;

public class util {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * 界面选择菜单
     */
    public static char readMenuSelection()
    {
        char c;
        for (;;)
        {
            String str = readKeyBoard(1,false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5')
            {
                System.out.println("输入错误,请重新输入!!!");
            }
            else{
                break;
            }
        }
        return c;
    }

    /**
     * 从键盘读取一个字符
     * @return
     */
    public static char readChar()
    {
        String str = readKeyBoard(1,false);
        return str.charAt(0);
    }
    /**
     * 从键盘读取字符,如果直接回车,则赋予一个默认值
     */
    public static char readChar(char defaultValue)
    {
        String str = readKeyBoard(1,false);
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }
    /**
     * 从键盘读取整数
     */
    public static int readInt()
    {
        int n;
        for (;;)
        {
            String str = readKeyBoard(2,false);
            try {
                n = Integer.parseInt(str);
                break;
            }catch (NumberFormatException e)
            {
                System.out.println("数字输入错误,请重新输入!!!");
            }
        }
        return n;
    }

    public static int readInt(int defaultValue)
    {
        int n;
        for (;;)
        {
            String str = readKeyBoard(2,false);
            if (str.equals(""))
            {
                return defaultValue;
            }
            try {
                n = Integer.parseInt(str);
                break;
            }catch (NumberFormatException e)
            {
                System.out.println("数字输入错误,请重新输入!!!");
            }
        }
        return n;
    }

    /**
     * 从键盘读取一个长度不超过limit的字符串
     * @param limit
     * @return
     */
    public static String readString(int limit)
    {
        return readKeyBoard(limit,false);
    }

    public static String readString(String defaultValue,int limit)
    {
        String str = readKeyBoard(limit,true);
        return str.equals("") ? defaultValue : str;
    }


    public static char readConfirmSelection()
    {
        char c;
        for (;;)
        {
            String str = readKeyBoard(1,false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N')
            {
                break;
            }
            else
            {
                System.out.println("选择错误,请重新输入!!!");
            }
        }
        return c;
    }
    private static String readKeyBoard(int limit,boolean blankReturn)
    {
        String line = "";
        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            if (line.length() == 0)
            {
                if (blankReturn)
                {
                    return line;
                }
                else{
                    continue;
                }
            }
            if (line.length() < 1 || line.length() > limit)
            {
                System.out.println("输入长度(不大于"+ limit +")错误,请重新输入!!!");
                continue;
            }
            break;
        }
        return line;
    }

}
