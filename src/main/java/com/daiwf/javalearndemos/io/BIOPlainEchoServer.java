package com.daiwf.javalearndemos.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOPlainEchoServer
{
    public void serve(int port) throws IOException {
        //将ServerSockect绑定到指定的端口里
        final ServerSocket socket = new ServerSocket();
        while (true) {
            //阻塞直到接收到新的客户端连接
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            new Thread(new Runnable()
            {
                @Override public void run() {
                    try {

                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                        //从客户端读取数据并原封不动会写回去
                        while (true) {
                            writer.println(reader.readLine());
                            writer.flush();
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

    public void improvedServe(int port) throws IOException {
        //将ServerSocket绑定到指定的端口里
        final ServerSocket socket = new ServerSocket(port);
        //创建一个线程池
        ExecutorService executorService =Executors.newFixedThreadPool(6);
        while(true){
            //阻塞直到新的客户端连接
            final Socket clientSocket =socket.accept();
            System.out.println("Accepted connection from "+clientSocket);
            //将请求提交给线程去执行
            executorService.execute(()->{

                try(BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    PrintWriter writer =new PrintWriter(clientSocket.getOutputStream(),true);
                    while(true){
                        writer.println(reader.readLine());
                        writer.flush();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
