package netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HelloServer {
    public static void main(String[] args) {
        //1.启动器 组装netty组建，启动服务
        new ServerBootstrap()
                //2.BossEventLoop WorkerEventLoop（selector，thread）group组
                .group(new NioEventLoopGroup())
                //3.选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                //4.boss 负责处理链接worker（child）负责处理读写，决定了woker（child）能执行哪些操作（handler）
                .childHandler(
                        //5.channel代表和客户端进行数据读写的通道 Initializer初始化，负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        //6.添加具体handler
                        nioSocketChannel.pipeline().addLast(new StringDecoder());//将ByteBuf转换为字符串
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){//自定义handler
                            @Override//读事件
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);//打印上一步转换好的字符串
                            }
                        });
                    }
                })
                //7.绑定服务端口
                .bind(8081);
    }
}
