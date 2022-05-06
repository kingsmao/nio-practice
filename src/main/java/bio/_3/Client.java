package bio._3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端，启动多个客户端时，服务端只能接收第一个启动的客户端请求
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        //使用输出流进行传输数据
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入：");
            String msg = sc.nextLine();
            printStream.println(msg);
            printStream.flush();
        }
    }
}
