/**
 * @program: 多线程：wait-阻塞队列-线程池
 * @description
 * @author: matilda
 * @create: 2020-06-21 10:20
 **/

import java.util.Scanner;

/**
 * 多生产者，单消费者
 * 例如：5个生产者，1个消费者
 */
public class MyBlockingQueueMain2 {
    private static final MyBlockingQueue queue = new MyBlockingQueue();

    static class Producer extends Thread {
        //给五个线程起名字
        Producer(int i) {
            super("生产者-" + i);
        }

        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    queue.push(i);
                    System.out.println(getName() + ": 放入: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 预期是，5 个线程加起来一共放 16 个数据之后就会全部阻塞，直到被消费
     * 结果是：消费者取出一个后，明明只有一个空位，但放入了5个
     * 明明消费者只调用了一次notify，但5个生产者都从wait中被唤醒了
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Producer(i).start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            //System.out.print("输入回车，进行消费");
            //scanner.nextLine();
            int i = queue.pop();
            System.out.println("消费者取出了: " + i);
        }
    }
}

//我们无法控制生产者明确唤醒消费者线程或者消费者明确唤醒生产者线程
//解决方法：
//1.生产者/消费者不要wait在用一对象上，各wait各的。这样，notify的时候也是各notify各的
//2.被唤醒之后，重新检查 条件，是否已经满足生产者被唤醒的时候检查下队列确定有空间吗
//被消费者唤醒<->队列有空间
//被生产者唤醒<->队列仍是满的


//当不再打印是，利用jconsole观察下线程中到底发生了什么事情