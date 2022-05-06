package bio.file;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 接收文件并保存
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("======socket服务端启动======");
            ServerSocket serverSocket = new ServerSocket(8888);
            //定义死循环，不断接收服务端（多个服务端）的请求
            ExecutorService es = Executors.newFixedThreadPool(1024);
            System.out.println("=== es ===");
            for (; ; ) {
                Socket accept = serverSocket.accept();
                //把socket对象交给线程池处理，避免重复创建线程，避免耗尽资源
                ServerSocketRunnable runnable = new ServerSocketRunnable(accept);
                es.execute(runnable);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("有人下线了");
        }
    }
}
