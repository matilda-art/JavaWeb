/**
 * @program: 多线程：wait-阻塞队列-线程池
 * @description
 *
 * 阻塞队列：
 * 1.首先是一个队列（有放的，有取的。取的时候并要不要求一定按照放的顺序放）
 *   FIFO/优先级队列（数据结构-堆）
 * 2.放的线程，如果当前队列是满的，则阻塞，直到队列中有空间放
 * 3.取的线程，如果当前队列是空的，则阻塞，直到队列中有数据取
 *      通过队列解耦，暂时缓解两边速度不一致的情况
 *
 * @author: matilda
 * @create: 2020-06-21 09:50
 **/
public class MyBlockingQueue {
    private int[] array = new int[1];      // 存放数据的数组
    private volatile int size = 0;         // 当前已有元素个数
    private int frontIndex = 0;            // 指向队首元素的下标
    private int rearIndex = 0;             // 指向下一个可用位置的下标

    public synchronized void push(int element) throws InterruptedException {
        // 判断队列是否满
        while (size >= array.length) {
            //throw new RuntimeException("队列已满");
            wait();     // 等着调用 pop 的线程唤醒，所以在 pop 中实现 notify
        }

        array[rearIndex] = element;
        size++;     // 破坏了原子性
        /*
        rearIndex = rearIndex + 1;
        if (rearIndex >= array.length) {
            rearIndex -= array.length;
        }
        */
        rearIndex = (rearIndex + 1) % array.length;
        notifyAll();   // 唤醒的是调用 pop 时阻塞的线程
    }

    public synchronized int pop() throws InterruptedException {
        while (size == 0) {
            //throw new RuntimeException("队列已空");
            wait();     // 等待着调用 push 的线程唤醒
        }

        int element = array[frontIndex];
        frontIndex = (frontIndex + 1) % array.length;
        size--;     // 破坏了原子性

        // 队列中一定出现空间了
        notifyAll();   // 唤醒调用 push 时阻塞的线程

        return element;
    }

    public int size() {
        return size;    // 可能有内存可见性问题
    }
}

//生产者：
//    1.队列满的时候wait，等待被消费者唤醒，pop中唤醒
//    2.有唤醒消费者的职责

//wait有可能被打断，一般建议放在while中