package bio._1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送消息，服务端接收消息
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("======socket服务端启动======");
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket accept = serverSocket.accept();
            //从字节输入流中获取数据
            InputStream inputStream = accept.getInputStream();
            //把字节输入流包装成缓冲字节输入流-->字节输入流转换成字符输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("服务端接收到：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
