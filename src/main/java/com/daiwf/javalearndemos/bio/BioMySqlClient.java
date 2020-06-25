package com.daiwf.javalearndemos.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BioMySqlClient
{

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        Scanner scanner =new Scanner(System.in);
        socket.getOutputStream().write(scanner.nextLine().getBytes());
        socket.close();
    }
}
