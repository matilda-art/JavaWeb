import java.util.Scanner;

/**
 * @program: 多线程：wait-阻塞队列-线程池
 * @description
 * @author: matilda
 * @create: 2020-06-21 09:58
 **/
/**
 * 单生产者，单消费者
 */
public class MyBlockingQueueMain {
    private static final MyBlockingQueue queue = new MyBlockingQueue();

    //消费者
    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    int i = queue.pop();
                    System.out.println("消费者取出: " + i);
                }
                /*
                //生产者
                for (int i = 0; true; i++) {
                    queue.push(i);
                    System.out.println("生产者放入: " + i);
                }
                */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        producer.start();

        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (true) {
            System.out.print("输入任何值，进行一次消费");
            scanner.nextLine();

            queue.push(i);
            System.out.println("生产者放入：" + i);
            i++;
            /*
            int i = queue.pop();
            System.out.println("消费者消费: " + i);
            */
        }
    }
}
