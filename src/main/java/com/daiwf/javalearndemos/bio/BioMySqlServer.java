package com.daiwf.javalearndemos.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioMySqlServer
{
    static  byte[] bs=new byte[1024];
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            //如果这边要支持并发就必须依赖多线程，来一个客户端请求就新增一个线程去阻塞
            //发生阻塞 放弃CPU
            Socket clientsocket = serverSocket.accept();
            //这边也阻塞了，这就产生了一个问题，只要有一个客户端连了这个服务端。其他客户端就连不上了。BIO不支持并发
            clientsocket.getInputStream().read(bs);
            System.out.println(new String(bs));
        }
    }
}
