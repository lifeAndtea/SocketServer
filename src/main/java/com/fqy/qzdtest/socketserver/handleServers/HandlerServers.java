package com.fqy.qzdtest.socketserver.handleServers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HandlerServers {
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(9999);
            ExucterPool exucterPool = new ExucterPool(3, 5);
            while (true) {
                Socket accept = serverSocket.accept();
                exucterPool.addTask(new HandleMessage(accept));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
