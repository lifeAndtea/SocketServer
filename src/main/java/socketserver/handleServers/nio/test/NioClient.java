package socketserver.handleServers.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        socketChannel.configureBlocking(false);
        //socketChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
        Scanner scanner = new Scanner(System.in);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            System.out.println("请说：");
            String msg = scanner.nextLine();
            buffer.put(msg.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
    }
}
