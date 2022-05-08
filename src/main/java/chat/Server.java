package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * bio模式下端口转发实现
 * 接收客服端socket请求
 * 保存socket请求
 * 将请求进来的socket内容广播给其他socket
 */
public class Server {
    public static List<Socket> sockets = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);
            for (; ; ) {
                Socket socket = ss.accept();
                sockets.add(socket);
                new ServerReaderThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
