package net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
            Socket client = server.accept();
            InputStream inputStream = new BufferedInputStream(client.getInputStream());
            OutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(new File("C:\\Users\\李昭\\Desktop\\sql2.txt")));
            byte[] flush = new byte[1024];
            int c;
            while ((c = inputStream.read(flush)) != -1) {
                outputStream.write(flush,0,c);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
