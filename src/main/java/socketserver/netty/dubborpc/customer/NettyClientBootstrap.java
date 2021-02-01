package socketserver.netty.dubborpc.customer;

import socketserver.netty.dubborpc.netty.NettyClient;
import socketserver.netty.dubborpc.publicapi.HelloService;

public class NettyClientBootstrap {

    public final static String providerName = "HelloService#hello#";

    public static void main(String[] args) {

        NettyClient customer = new NettyClient();

        HelloService helloServide = (HelloService) customer.getBean(HelloService.class, providerName);

        String hello = helloServide.hello("hello dubbo~");

        System.out.println("调用结果："+hello);
    }
}
