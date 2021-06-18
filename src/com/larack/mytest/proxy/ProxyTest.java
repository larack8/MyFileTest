package com.larack.mytest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyTest {

    interface ISubject {

        public void speak(String name);
    }

    final static class RealSubject implements ISubject {

        @Override
        public void speak(String name) {
            System.out.println("hello: " + name);
        }
    }

    static class DynamicProxy implements InvocationHandler {

        private  Object subject;

        public DynamicProxy(Object subject) {
            this.subject = subject;
        }

        public Object invoke(Object object, Method method, Object[] args) throws Throwable {
            return method.invoke(subject, args);
        }

    }

    static class MySubject implements ISubject {

        @Override
        public void speak(String name) {
            System.out.println("MySubject speak " + name);
        }
    }

    static class MyProxy implements InvocationHandler {

        private Object mObj;

        public MyProxy(Object mObj) {
            this.mObj = mObj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            method.invoke(mObj, args);
            return null;
        }
    }

    public static void main(String[] args) {

        ISubject realSubject = new RealSubject();

        InvocationHandler handler = new DynamicProxy(realSubject);

        ISubject subject = (ISubject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);

        System.out.println(subject.getClass().getName());

        subject.speak("world");
    }


    public static void test2() {

        ISubject subject = new MySubject();
        InvocationHandler invocationHandler =new MyProxy(subject);

    }

}
