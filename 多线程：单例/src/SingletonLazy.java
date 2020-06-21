/**
 * @program: 多线程：单例，wait，阻塞队列
 * @description
 * @author: matilda
 * @create: 2020-06-19 19:26
 **/
//懒汉模式：延迟初始化 —— 按需加载
    //不是线程安全

public class SingletonLazy {
    // 1. 需要保存该类唯一对象的引用
    private static SingletonLazy instance = null;

    //synchronized 方法保证了线程安全
    public synchronized static SingletonLazy getInstance(){
        if (instance == null){
            instance = new SingletonLazy();
        }
        return instance;
    }

    public static SingletonLazy getInstanceUnsafe() {
        // getInstance 被调用的时候，代表有人需要
        // 该类的对象了
        // 所以应该实例出对象出来了 —— 按需加载

        /*
        if (instance == null) {
            // 代表对象还没有被初始化
            // 需要先初始化
            instance = new SingletonLazy();
            return instance;
        } else {
            // 代表 instance 已经指向了一个对象了
            // 所以我们不需要实例化对象了
            // 直接返回该对象的引用即可
            return instance;
        }
        */
        if (instance == null) {//判断的时候instance还是null
            //到这里实例化时，instance有可能已经不是null了
            instance = new SingletonLazy();
        }
        return instance;
    }

    private SingletonLazy() {}
}
