package com.daiwf.javalearndemos.gmssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version [版本号，2021/6/7 0007]
 * @文件名 Server
 * @作者 daiwf
 * @创建时间 2021/6/7 0007 10:27
 * @版权 Copyright Epoint Tech. Co. Ltd. All Rights Reserved.
 * @描述 []
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2000);


        System.out.println("服务器准备就绪～");
        System.out.println("服务器信息：" + server.getInetAddress() + " P:" + server.getLocalPort());


        // 等待客户端连接
        for (; ; ) {
            // 得到客户端
            Socket client = server.accept();
            // 客户端构建异步线程
            ClientHandler clientHandler = new ClientHandler(client);
            // 启动线程
            clientHandler.start();
        }

    }

    /**
     * 客户端消息处理
     */
    private static class ClientHandler extends Thread {
        private Socket socket;


        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("新客户端连接：" + socket.getInetAddress() +
                    " P:" + socket.getPort());

            try {
                // 得到打印流，用于数据输出；服务器回送数据使用
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                // 得到输入流，用于接收数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));


                // 客户端拿到一条数据
                String str = socketInput.readLine();

                // 打印到屏幕。
                System.out.println("收到客户端数据：" + str);
                GMTLSTCPBiz gmtlstcpBiz = new GMTLSTCPBiz();
                String rtn = gmtlstcpBiz.SecureTransport(str);
                System.out.println("返回客户端数据：" + rtn);
                socketOutput.println(rtn);


                socketInput.close();
                socketOutput.close();

            } catch (Exception e) {
                System.out.println("连接异常断开");
            } finally {
                // 连接关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("客户端已退出：" + socket.getInetAddress() +
                    " P:" + socket.getPort());

        }
    }
}
