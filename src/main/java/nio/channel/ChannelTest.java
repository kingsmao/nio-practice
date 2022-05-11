package nio.channel;


import org.junit.Test;

import java.io.*;
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

    @Test
    public void copy() throws Exception {
        File file = new File("/Users/xx/Desktop/nio/read/socket.txt");
        File fileNew = new File("/Users/xx/Desktop/nio/read/socket_new.txt");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(fileNew);
        FileChannel fileChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            //先清空缓冲区
            buffer.clear();
            int flag = fileChannel.read(buffer);
            if (flag == -1) {
                break;
            }
            buffer.flip();
            fosChannel.write(buffer);
        }
        fileChannel.close();
        fosChannel.close();
        System.out.println("复制完成！");
    }

    /**
     * 多个缓冲区进行读写
     */
    @Test
    public void mulitCopy() throws Exception {
        //定义通道
        FileInputStream fis = new FileInputStream("data01.txt");
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("data02.txt");
        FileChannel fosChannel = fos.getChannel();

        //定义多个缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(256);
        ByteBuffer buffer2 = ByteBuffer.allocate(256);

        ByteBuffer[] buffers = {buffer1,buffer2};
        //分批读
        fisChannel.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip();
            System.out.println(new String(buffer.array(),0,buffer.remaining()));
        }

        //分批写入
        fosChannel.write(buffers);
        fisChannel.close();
        fosChannel.close();

        System.out.println("分批写入成功！");
    }

    @Test
    public void channel_copy() throws Exception {
        //定义通道
        FileInputStream fis = new FileInputStream("data01.txt");
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("data04.txt");
        FileChannel fosChannel = fos.getChannel();

        //使用通道复制
        //fosChannel.transferFrom(fisChannel,fisChannel.position(),fisChannel.size());
        fisChannel.transferTo(fisChannel.position(), fisChannel.size(),fosChannel);

        fisChannel.close();
        fosChannel.close();
        System.out.println("使用通道复制成功！");
    }
}
