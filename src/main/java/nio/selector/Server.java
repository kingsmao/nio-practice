package nio.selector;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * NIO非阻塞通信案例
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //1 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2 切换成非阻塞
        ssChannel.configureBlocking(Boolean.FALSE);
        //3 绑定端口
        ssChannel.bind(new InetSocketAddress(9999));
        //4 生成selector
        Selector selector = Selector.open();
        //5 将通道注册到selector上，并且开始监听接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

    }
}
