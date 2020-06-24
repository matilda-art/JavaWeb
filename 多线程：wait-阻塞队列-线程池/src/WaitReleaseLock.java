/**
 * @program: 多线程：阻塞队列-线程池
 * @description
 * @author: matilda
 * @create: 2020-06-21 09:18
 **/
public class WaitReleaseLock {
    static final Object o1 = new Object();
    static final Object o2 = new Object();

    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                synchronized (o1) {
                    synchronized (o2) {
                        o2.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread subThread = new SubThread();
        subThread.start();

        // 主动放弃CPU，保证让子线程运行起来
        Thread.sleep(1000);

        // 子线程已经运行起来了
        synchronized (o2) {
            System.out.println("o2 加锁成功");
        }

        synchronized (o1) {
            System.out.println("o1 加锁成功");
        }
    }
}

//sleep（毫秒）和wait（毫秒）的区别？
//相同点：
//都会主动放弃CPU，并且在一段实际中不再接受调度（抢占CPU）
//TIMED_WAITING
//不同点：
//1.sleep是线程休眠一段时间。 wait是等待被其他线程唤醒，这里还带了一个超时时间。
//2.sleep是属于static的静态方法：static Thread.sleep。 wait是属于Object的普通方法：Object.wait。
//3.sleep任何时候都可以。wait要先对Object加锁。
//4.sleep一个锁都不释放。wait要先释放Object锁。