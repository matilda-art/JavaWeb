/**
 * @program: 多线程：Synchronized-volatile关键字
 * @description
 * @author: matilda
 * @create: 2020-06-20 11:02
 **/
//volatile保证内存可见性
    //内存可见性：被volatile修饰的变量：
    //1.任何的读取，必须从主内存中读取
    //2.任何的修改，必须同步回主内存中去
public class VolatilePromiseVisibleDemo {
    //run这个变量（静态属性）是共享的
    private static boolean run = true;

    static class Runner extends Thread{
        @Override
        public void run() {
            //run变成false才会退出
            //因为内存可见性的问题，子线程看不到run的值被修改了
            while (run){
            }
            System.out.println("Runner退出了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        runner.start();

        //主线程放弃2sCPU,保证后边的代码执行时，子线程已经运行起来了
        Thread.sleep(2000);

        System.out.println("2s已到");
        run = false;
        //正确的情况下，主线程就到这里退出了
        //随后，因为run == false，所以，子线程也会退出
        //进而JVM运行结束
    }
}
