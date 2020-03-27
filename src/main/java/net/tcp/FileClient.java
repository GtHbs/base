package net.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("localhost",8888);
            InputStream stream = new BufferedInputStream(
                    new FileInputStream(new File("C:\\Users\\李昭\\Desktop\\sql.txt")));
            OutputStream outputStream = new BufferedOutputStream(client.getOutputStream());
            byte[] bytes = new byte[1024];
            int c ;
            while ((c = stream.read()) != -1) {
                outputStream.write(bytes,0,c);
            }
            outputStream.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
