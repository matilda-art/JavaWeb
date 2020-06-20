import java.util.Scanner;

/**
 * @program: 多线程：单例，wait，阻塞队列
 * @description
 * @author: matilda
 * @create: 2020-06-20 17:11
 **/
public class WaitNotifyDemo {
    private static final Object o = new Object();
    //两个线程，子线程打印->wait等待唤醒->继续打印
    static class SubThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println("等待之前");
                synchronized (o){
                    o.wait();
                }
                System.out.println("等待之后");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //通过scanner等用户输入，用户输入任意字符，就唤醒子线程
    public static void main(String[] args) {
        SubThread thread = new SubThread();
        thread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();//至少一段时间之后
        synchronized (o){
            o.notifyAll();//o.notify();
        }
    }

/*    //必须先加锁，才能用wait/notify
    //必须对wait/notify的对象加锁

    //为什么需要先加锁，再wait/notify？
    //wait/notify都需要修改该对象的等待集，而该对象的等待集是共享的。
    //所以为了保证多线程环境下线程安全，所以先把对象锁住，再修改等待集，就没问题了
    public static void main(String[] args) throws InterruptedException {
        Object p = new Object();
        Object q = new Object();

        //p.wait();会抛异常

        //会抛异常
*//*        synchronized (q){
            p.wait();
        }*//*

        synchronized (p) {
            p.wait();
        }
    }*/
}

//1.都是Object的普通方法
//2.wait有超时时间的变形
//3.notify有notifyAll变形，唤醒随机的一个/全部唤醒
//4.使用之前，必须对对象加锁，并且是wait/notify的那个对象
