package socketserver.handleServers.nio.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\qzd\\Pictures\\Camera Roll\\93035f26-f677-4b69-adb5-1ded1a3cb6fb.gif");
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\qzd\\Pictures\\Camera Roll\\2.gif");
            FileChannel inChannel = fileInputStream.getChannel();
            FileChannel outChannel = fileOutputStream.getChannel();
            /*ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }*/

            //Transferto()
            long l = inChannel.transferTo(inChannel.position(), inChannel.size(), outChannel);
            System.out.println(l);

            //TransferFrom
            //outChannel.transferFrom(inChannel,inChannel.position(),inChannel.size());

            inChannel.close();
            outChannel.close();
            System.out.println("copy success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
