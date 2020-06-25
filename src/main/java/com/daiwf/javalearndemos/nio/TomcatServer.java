package com.daiwf.javalearndemos.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class TomcatServer
{
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);
    //这边的目的是把历史的SocketChannel都保存起来，每次请求的
    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);

            serverSocketChannel.bind(socketAddress);
            //一开始就要设置一次非阻塞
            serverSocketChannel.configureBlocking(false);
            while (true) {

                for (SocketChannel channel : channelList) {
                    int read = channel.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("read:" + read);
                        byteBuffer.flip();
                        byte[] bs = new byte[read];
                        byteBuffer.get(bs);
                        String content = new String(bs);
                        System.out.println("输出内容：" + content);
                        byteBuffer.flip();
                    }else if(read==-1){
                        //如果断开了就把channel从list删除,能省点就省点吧
                        channelList.remove(channel);
                    }
                }
                SocketChannel accept = serverSocketChannel.accept();
                if (accept != null) {
                    System.out.println("conn success!");
                    //这边接收到了就设置为非阻塞，否则下一轮的无法接受。
                    accept.configureBlocking(false);
                    channelList.add(accept);
                    System.out.println("channellistsize" + channelList.size());

                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
