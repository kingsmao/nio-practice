package bio._3;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端可以反复接收消息，客户端可以反复发送消息
 * 服务端可以接受多个服务端的请求，每接收一个服务端就启一个线程
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("======socket服务端启动======");
            ServerSocket serverSocket = new ServerSocket(9999);
            //定义死循环，不断接收服务端（多个服务端）的请求
            for (; ; ) {
                Socket accept = serverSocket.accept();
                new ServerThreadReader(accept).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
