package exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 李昭
 * 异常分类:RuntimeException
 *        CheckedException
 */
public class Test01 {
    public static void main(String[] args) {
        //System.out.println(1 / 0);      //RuntimeException
        FileReader reader = null;
        try {
            reader = new FileReader("C:/Users/李昭/Desktop/sql.txt");
            char word = (char) reader.read();
            System.out.println(word);
        } catch (FileNotFoundException e) {     //子类
            e.printStackTrace();
        } catch (IOException e) {               //父类
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

        }finally {

        }
    }
}
