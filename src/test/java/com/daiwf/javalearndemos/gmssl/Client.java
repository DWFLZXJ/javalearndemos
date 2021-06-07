package com.daiwf.javalearndemos.gmssl;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @version [版本号，2021/6/7 0007]
 * @文件名 Client
 * @作者 daiwf
 * @创建时间 2021/6/7 0007 10:27
 * @版权 Copyright Epoint Tech. Co. Ltd. All Rights Reserved.
 * @描述 []
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        // 超时时间
        socket.setSoTimeout(300000);

        // 连接本地，端口2000；超时时间3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);

        System.out.println("已发起服务器连接，并进入后续流程～");
        System.out.println("客户端信息：" + socket.getLocalAddress() + " P:" + socket.getLocalPort());
        System.out.println("服务器信息：" + socket.getInetAddress() + " P:" + socket.getPort());

        try {
            // 发送接收数据
            todo(socket);
        } catch (Exception e) {
            System.out.println("异常关闭"+e.getMessage());
        }

        // 释放资源
        socket.close();
        System.out.println("客户端已退出～");

    }

    private static void todo(Socket client) throws IOException {

        // 得到Socket输出流，并转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);


        // 得到Socket输入流，并转换为BufferedReader
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // 键盘读取一行
            String str = "{ \"port\":\"2000\",\"content\":\"hellodaiwf\"}";
            // 发送到服务器
            socketPrintStream.println(str);

            // 从服务器读取一行
            String echo = socketBufferedReader.readLine();
            System.out.println(echo);

        // 资源释放
        socketPrintStream.close();
        socketBufferedReader.close();

    }


}
