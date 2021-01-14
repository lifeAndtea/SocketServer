package socketserver.handleServers.fileUpload;

import java.io.*;
import java.net.Socket;

public class UploadClient {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\qzd\\Pictures\\石头.png");
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(".png");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes))>0){
                dataOutputStream.write(bytes,0,len);
            }
            dataOutputStream.flush();
            dataOutputStream.close();
            fileInputStream.close();
            socket.shutdownOutput();
            System.out.println("发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
