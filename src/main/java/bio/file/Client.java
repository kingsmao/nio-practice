package bio.file;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 上传任意格式文件
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        //先发送文件的格式（后缀）
        dos.writeUTF(".png");
        //再发送文件
        FileInputStream is = new FileInputStream("/Users/xxx/Desktop/nio/read/500s.png");
        byte[] bytes = new byte[1024];
        int len;
        while ((len = is.read(bytes)) > 0) {
            dos.write(bytes,0,len);
        }
        dos.flush();
        socket.shutdownOutput();//通知服务端数据发送完毕
    }
}
