package socketserver.handleServers;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            while (true){
                String msg = scanner.nextLine();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
