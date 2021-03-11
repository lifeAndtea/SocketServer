package com.sqy;

import com.fqy.SockerServerApplication;
import com.fqy.qzdtest.socketserver.netty.websocket.SocketServer;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class SomethingTest {


    @Test
    public void likeAop(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SockerServerApplication.class);
        //SocketServer maybe = context.getBean("maybe", SocketServer.class);
        SocketServer server = context.getBean(SocketServer.class);
        //maybe.destory();
    }
}
