/**
 * @program: 线程：状态流转和安全
 * @description
 * @author: matilda
 * @create: 2020-06-16 19:36
 **/

/*
 * 两个线程同时打印
 */
    //yield：本质是放弃抢CPU，但是没有放弃抢CPU的资格
public class YieldDemo {
    static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("A");
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("B");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.start();
        b.start();
    }
}
