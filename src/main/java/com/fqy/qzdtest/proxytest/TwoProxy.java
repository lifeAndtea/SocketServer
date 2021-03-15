package com.fqy.qzdtest.proxytest;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class TwoProxy {
    public static void main(String[] args) {

        //jdk
        //jdkProxy();

        //chlib
        cglibProxy();

    }

    public static void jdkProxy(){

        /*JDKProxy jdkProxy = new JDKProxy();
        IWork leader = (IWork)jdkProxy.newProxy(new Leader());
        leader.evaluate("steve");
        leader.meeting();*/

        Leader leader = new Leader();
        JDKProxy jdkProxy = new JDKProxy();
        IWork o = (IWork)jdkProxy.newProxy(leader);
        o.evaluate("sdf");
        o.meeting();
    }


    public static void cglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Leader.class);
        enhancer.setCallback(new CGLibProxy());
        Leader leader = (Leader)enhancer.create();
        leader.meeting();
        leader.evaluate("sdfdsf");

        /*CGLibProxy cgLibProxy = new CGLibProxy();
        Leader proxyObject = (Leader) cgLibProxy.createProxyObject(new Leader());
        proxyObject.evaluate("sdf");
        proxyObject.meeting();*/
    }

}
