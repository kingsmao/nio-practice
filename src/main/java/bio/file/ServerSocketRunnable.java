package bio.file;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

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
            DataInputStream dis = new DataInputStream(inputStream);

            //读取客户端发送过来的文件类型
            String suffix = dis.readUTF();
            System.out.println("threadName=" + threadName + " 服务端接收到文件，格式=" + suffix);
            FileOutputStream os = new FileOutputStream("/Users/xxx/Desktop/nio/write/" + UUID.randomUUID().toString() + suffix);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = dis.read(bytes)) > 0) {
                os.write(bytes,0,len);
            }
            os.close();
            System.out.println("threadName=" + threadName + " 服务端接收到文件保存成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
