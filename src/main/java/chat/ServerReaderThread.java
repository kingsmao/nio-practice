package chat;

import java.io.*;
import java.net.Socket;

public class ServerReaderThread extends Thread{
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            InputStream inputStream = socket.getInputStream();
            //从字节输入流中获取数据
            //把字节输入流包装成缓冲字节输入流-->字节输入流转换成字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("threadName" + threadName +  " 服务端接收到：" + msg);
                sentToAllClient(msg);
            }
        } catch (IOException e) {
            System.out.println("有人下线了");
            Server.sockets.remove(socket);
        }
    }

    private void sentToAllClient(String msg) throws IOException {
        for (Socket socket : Server.sockets) {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(msg);
            ps.flush();
        }

    }
}
