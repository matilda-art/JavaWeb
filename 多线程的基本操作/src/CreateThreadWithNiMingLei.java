/**
 * @program: 多线程基本操作
 * @description
 * @author: matilda
 * @create: 2020-06-15 18:54
 **/
public class CreateThreadWithNiMingLei {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("我是子线程");
                }
            }
        };
        t.start();

        while (true){
            System.out.println("我是main线程");
        }
    }
}
