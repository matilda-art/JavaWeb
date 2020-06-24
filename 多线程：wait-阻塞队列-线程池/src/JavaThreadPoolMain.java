import java.util.Queue;
import java.util.concurrent.*;

/**
 * @program: 多线程：wait-阻塞队列-线程池
 * @description
 * @author: matilda
 * @create: 2020-06-24 14:55
 **/

//线程池：
//1.what和why
//why：减少了创建销毁线程的频率，进而减少程序开销
public class JavaThreadPoolMain {

    static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"我随便起的名字");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);

        //创建线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                10,//正式员工
                10,//正式＋临时
                0,
                TimeUnit.SECONDS,
                queue,
                new MyThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );

        for (int i = 0; true ; i++) {
        //创建让线程池执行的任务
        Runnable target = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

            //等同于把任务提交给线程池
            //线程池内部都会选择一个空闲的线程去执行该任务
            System.out.println("提交第"+i+"个任务");
            System.out.println(queue.size());
            threadPool.execute(target);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

//关于ThreadPoolExecutor的构造方法的参数理解（重点）

//为什么需要线程工厂： —— 因为职责被分离了
//创建线程的过程在线程池代码内部，我们无法控制，如果想改个名字之类的，没有机会
//所以线程提供了一个工厂，供我们使用，创建线程的时候有机会调用我们的方法