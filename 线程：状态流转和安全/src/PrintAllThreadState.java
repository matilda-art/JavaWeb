/**
 * @program: 线程：状态流转和安全
 * @description
 * @author: matilda
 * @create: 2020-06-17 20:47
 **/

//打印出线程所有的状态
public class PrintAllThreadState {
    public static void main(String[] args) {
        Thread.State[] values = Thread.State.values();
        for (Thread.State s:values) {
            System.out.println(s);
        }
    }
}
