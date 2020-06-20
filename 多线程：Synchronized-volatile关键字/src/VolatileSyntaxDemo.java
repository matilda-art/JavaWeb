/**
 * @program: 多线程：Synchronized-volatile关键字
 * @description
 * @author: matilda
 * @create: 2020-06-20 10:53
 **/
public class VolatileSyntaxDemo {
    /**
     * volatile 修饰变量的定义
     * volatile 修饰的是共享的变量（属性 和 静态属性的定义）
     */
    volatile int a;
    static volatile int sa;
}
//作用：原子性/内存可见性/代码重排序
//永远保证主内存的值是最新的，并且每次任何时候都是读最新的值

//volatile的作用：
//1.解决内存可见性的问题 —— 被volatile修饰的变量，永远读到最新值
//2.解决原子性问题 —— double/long的赋值中的原子性
//3.约束代码重排序 —— volatile Object o = new Object(); 被volatile修饰，仅仅实例化的时候不能被重排序
