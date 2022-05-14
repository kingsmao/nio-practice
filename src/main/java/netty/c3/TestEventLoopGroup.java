package netty.c3;

import io.netty.util.NettyRuntime;

public class TestEventLoopGroup {
    public static void main(String[] args) {
        int i = NettyRuntime.availableProcessors();
        System.out.println(i);

    }
}
