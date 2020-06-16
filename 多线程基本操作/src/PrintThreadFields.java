/**
 * @program: 多线程基本操作
 * @description
 * @author: matilda
 * @create: 2020-06-15 18:54
 **/

public class PrintThreadFields {
    //自己定义一个继承自Thread的类，只有继承自Thread的类，才能称为一个线程类
    static class SubThread extends Thread{
        @Override
        public void run() {
            printFields();
        }
    }

    public static void main(String[] args) {
        Thread thread = new SubThread();//构造该类的对象
        thread.start();//调用对象的start()

        printFields();
    }

    //哪个线程调用该方法，就打印哪个线程的属性
    private static void printFields() {
        Thread t = Thread.currentThread();
        long id = t.getId();

        System.out.println("线程的名字：" + id + ":" + t.getName());
        System.out.println("线程的优先级：" + id + ":" + t.getPriority());
        System.out.println("线程的状态：" + id + ":" + t.getState());
        System.out.println("线程是否存活：" + id + ":" + t.isAlive());
        System.out.println("线程是否是后台线程：" + id + ":" + t.isDaemon());
    }
}
