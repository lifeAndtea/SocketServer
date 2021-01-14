package socketserver.handleServers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class HandleMessage implements Runnable {
    private Socket socket;

    public HandleMessage(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String message;
            while (true) {
                if ((message = bufferedReader.readLine()) != null) {
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
