import java.util.concurrent.TimeUnit;

/**
 * @program: 线程：状态流转和安全
 * @description
 * @author: matilda
 * @create: 2020-06-16 18:38
 **/

//Thread.sleep
    //让线程休眠一段时间，然后再执行
    //让线程放弃CPU，等待一段时间之后，重新就绪，重新排队等待分配CPU
    //sleep很难精确，能做到超过要求的时间
public class SleepDemo {
    public static void main(String[] args) {
        System.out.println(1);
        try{
            Thread.sleep(1);//毫秒为单位
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(2);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(3);
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(4);
    }
}
