/**
 * @program: 多线程：Synchronized关键字
 * @description
 * @author: matilda
 * @create: 2020-06-18 20:59
 **/
 
public class FixUnsafe {
    private static final long N = 100_0000_0000L;
    private static long v = 0;

    static class Add extends Thread {
        @Override
        public void run() {
            for (long i = 0; i < N; i++) {
                synchronized (FixUnsafe.class) {
                    v++;
                }
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            for (long i = 0; i < N; i++) {
                synchronized (FixUnsafe.class) {
                    v--;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add a = new Add();
        Sub s = new Sub();

        a.start();
        s.start();

        // 为什么这里需要 join？
        a.join();
        s.join();

        System.out.println(v);
    }
}

//释放锁并不意味着释放CPU
//释放锁之后可以保证公平性，让先等待的线程请求到锁
//      也可以不保证公平性，只管释放，把所有线程都释放成就绪态，大家再去抢
