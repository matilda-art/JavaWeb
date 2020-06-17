import java.util.concurrent.TimeUnit;

/**
 * @program: 线程：状态流转和安全
 * @description
 * @author: matilda
 * @create: 2020-06-16 19:15
 **/

public class ThreadStateTransfer {
    static class SubThread extends Thread {
        @Override
        public void run() {
            /*
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();//阻塞在这里，输入任何内容，该方法返回
            System.out.println("子线程即将退出");
            */

           /* try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            try{
                for (int i = 0; i < 50; i++) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//子线程走到这里终止
    }

    public static void main(String[] args) throws InterruptedException {
        Thread p = new SubThread();
        System.out.println(p.getState());
        p.start();
        System.out.println(p.getState());
        //isAlive() -> 线程是否存活：一个线程的状态不是NEW或者不是TERMINATED，则线程的isAlive()==true
        while (p.isAlive()) {
            System.out.println(p.getState());//看到的是RUNNABLE还是TIMED_WAITING
                                             //理论上是能看到RUNNABLE的
                                             //但实际运行中，基本是看不到RUNNABLE
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(p.getState());
    }
}

//多态的知识点——向上转型
//引用类型：
//类类型的引用（class type reference） 指向该类及其子类，对象
//接口类型的引用（interface type reference） 指向该接口的实现类，对象
//数组类型的引用（array type reference）

//如果用到了SubThread中独有的方法时，需要使用SubThread类型的 引用，才可以通过编译
//如果没有用到SubThread中独有的方法，用的是继承下来的方法时两个都可以。