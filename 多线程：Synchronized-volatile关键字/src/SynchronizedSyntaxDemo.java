/**
 * @program: 多线程：Synchronized关键字
 * @description
 * @author: matilda
 * @create: 2020-06-18 19:27
 **/

/**
 * 演示 synchronized 关键字的语法
 */
public class SynchronizedSyntaxDemo {
    /**
     * 修饰方法：同步方法
     */
    //普通方式
    public synchronized void method() {
    }
    //等同于 普通方法() {  sync(this) {}   }

    //静态方法
    public static synchronized void staticMethod() {
    }
    //等同于 静态方法() {  sync (类.class) {}  }

    /**
     * 修饰代码块：同步代码块
     * 起到让多个线程之间产生同步的作用 —— 通过锁
     *
     * 判断是否同步：1.调用的方法是不是都有加锁动作 2.加锁是不是加的同一把锁（同一对象）
     */
    public void otherMethod() {
        // 括号里跟着指向对象的引用，引用不能是 null
        // 只要是引用就行，没有任何要求
        Object o = new Object();
        synchronized (o) {
        }

        synchronized (this) {
        }

        // 反射中，指向当前类对象
        synchronized (SynchronizedSyntaxDemo.class) {
        }
    }
}

//synchronized可以达到的效果 —— 原子性/可见性/代码重排序
//请求锁成功：JVM规定，刷新线程的工作内存——保证以后读到的数据都是最新的
//释放锁：JVM规定，必须把线程的工作内存的数据，刷新回主内存——保证其他线程读到你最新的修改

//synchronized加锁失败会进入BLOCKED状态 —— 专为synchronized设置的一种状态