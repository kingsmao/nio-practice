package nio.channel;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

    /**
     * 通过channel写入数据
     */
    @Test
    public void write(){
        try {
            //1.字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("data01.txt");
            //2.申请通道
            FileChannel channel = fos.getChannel();
            //3.分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello nio!".getBytes());
            //4.把缓冲区切换成写出模式
            buffer.flip();
            channel.write(buffer);
            channel.close();
            System.out.println("写数据到文件中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过channel读取数据
     */
    @Test
    public void read(){
        //1.定义一个文件输入流
        try {
            FileInputStream fis = new FileInputStream("data01.txt");
            //2.定义通道
            FileChannel channel = fis.getChannel();
            //3。缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            buffer.flip();
            String s = new String(buffer.array(),0,buffer.remaining());
            System.out.println(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
