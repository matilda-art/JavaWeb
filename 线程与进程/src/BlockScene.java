import java.util.Scanner;

/**
 * @program: 多线程
 * @description
 * @author: matilda
 * @create: 2020-06-14 11:49
 **/

/*
计算斐波那契数列（计算时间长）
主线程负责通过scanner读取用户的输入
专门启动其他线程，来负责计算
*/

public class BlockScene {
    // 时间复杂度是 O(2 ^ n)
    // 非常大的时间复杂度
    private static long fib(int n) {
        if (n < 2) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    private static class FibThread extends Thread {
        private final int n;

        FibThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.printf("fib(%d) = %d%n", n, fib(n));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 主线程只负责接待客人
            int n = scanner.nextInt();
            //System.out.printf("fib(%d) = %d%n", n, fib(n));
            new FibThread(n).start();   // 每次计算交给一个新人去处理
        }
    }
}

