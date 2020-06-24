import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @program: 多线程：wait-阻塞队列-线程池
 * @description
 * @author: matilda
 * @create: 2020-06-24 14:39
 **/
public class JavaBlockingQueueMain {

    //数组实现，循环实现，有容量上限 (先进先出)
    static BlockingQueue<Integer> q1 = new ArrayBlockingQueue<>(10);
    //链表实现，队列没有容量上限  Integer.MAX_VALUE (先进先出)
    static BlockingQueue<Integer> q2 = new LinkedBlockingQueue<>();
    //堆实现，优先级阻塞队列
    static BlockingQueue<Integer> q3 = new PriorityBlockingQueue<>();

    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    q1.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Producer().start();
        }

        while (true){
            Integer take = q1.take();
            System.out.println(take);
        }
    }
}
