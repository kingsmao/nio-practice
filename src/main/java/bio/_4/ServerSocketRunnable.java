package bio._4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerSocketRunnable implements Runnable{
    private final Socket socket;

    public ServerSocketRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            InputStream inputStream = socket.getInputStream();
            //从字节输入流中获取数据
            //把字节输入流包装成缓冲字节输入流-->字节输入流转换成字符输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("threadName" + threadName +  " 服务端接收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
