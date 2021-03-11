package com.fqy.qzdtest.socketserver.handleServers.fileUpload;

import cn.hutool.core.util.RandomUtil;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HandleFile implements Runnable {
    private Socket socket;

    public HandleFile(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String s = dataInputStream.readUTF();
            s = "C:\\Users\\qzd\\Pictures\\" + RandomUtil.randomString(5) + s;
            FileOutputStream fileOutputStream = new FileOutputStream(s);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = dataInputStream.read(bytes)) > 0) {
                fileOutputStream.write(bytes, 0, len);
            }
            dataInputStream.close();
            fileOutputStream.close();
            System.out.println("接收成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
