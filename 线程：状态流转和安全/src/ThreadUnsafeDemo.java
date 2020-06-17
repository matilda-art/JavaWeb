/**
 * @program: 线程：状态流转和安全
 * @description
 * 启动两个线程，同时操作一个变量 v = 0
 * 一个线程对该变量执行 N 次 v++
 * 另一个线程对该变量执行 N 次 v--
 * 问，当两个线程都执行结束时，v 的值是多少？
 * 期望值是多少？  0
 * 实际值是多少？  随机值（N的值比较小时，出现随机值的情况较少，N大时，出现的情况较多）
 * @author: matilda
 * @create: 2020-06-16 19:49
 **/

public class ThreadUnsafeDemo {
    private static final int N = 100_0000;
    private static int v = 0;//因为是静态属性，所以共享
                             //因为Add/Sub线程都会修改v
                             //所以才会产生线程安全问题，需要想办法解决

    static class Add extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                v++;//加载变量到CPU的寄存器上，让CPU把寄存器上的值+1，把计算结果保存回内存上
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                v--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add a = new Add();
        Sub s = new Sub();
        a.start();
        s.start();

        a.join();
        s.join();
        // 这里时，a 和 s 都结束了
        System.out.println(v);
    }
}

//什么情况下会出现线程不安全？
//1.线程之间有共享的数据的。换言之，线程之间不存在共享，天生是线程安全的。
//2.即使线程之间有共享，但没有线程修改共享数据，则天生是线程安全的。

//JVM的运行时内存区域中，哪些位置是共享的，哪些是线程内部私有的？
//1.nextPC值 —— PC区域     私有的
//2.栈区（java栈/native栈）   私有的
//3.堆区                  共享的
//4.方法区                 共享的
//5.运行时常量池             共享的

//变量有几个特征：
//类型：
//   基本数据类型：byte/short/int/long/float/double/boolean/char
//   引用数据类型：class/interface/array

//局部变量/对象的属性/类的静态属性

//变量的类型特征不能决定变量保存在哪个内存区域中！

//局部变量 —— 栈帧@栈
//对象的属性 —— 对象@堆
//类的静态属性 —— 类@方法区

//局部变量是线程私有的数据，不会共享，不需要特别考虑线程安全问题
//属性/对象 + 静态属性/类，是共享的，需要考虑线程安全问题


//基本数据类型 和 引用数据类型的区别是什么？
//一旦运行起来，所有的变量，本质上在JVM看起来就是一个块内存。
//基本数据类型的值的使用，是直接过程。引用数据类型的值的使用，是间接过程。

//什么时候需要特别考虑线程安全问题？
//线程之间有修改 静态属性 or 同一个对象的属性时，需要专门通过一些机制保障线程安全。否则一般不需要考虑。


//关于带来线程不安全的三个重要知识点：
//1.原子性 —— 认为原子不可以再分割。一组需要保证不能再分割的操作，就称为保证原子性。
    // JVM设计时，是按照32bit为最小操作单位设计的
    //byte 8; short 16; int 32; long 64; float 32; double 64; char 16; boolean <32
//2.代码可见性 —— 内存可见性问题。由于高速缓存（cache）的存在，导致不一定看到最新的结果，进而导致问题。
//3.代码重排序


//java内存模型（JMM）规定：
//所有线程都有自己的工作内存。线程要操作任何数据，首先需要把数据从主内存中加载到工作内存中。——有专用的字节码
//线程就在工作内存中进行操作
//在合适的机会，把计算结果，同步回工作内存中