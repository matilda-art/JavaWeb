package tcp.echo;

import java.util.Date;

/**
 * @program: TCP的Socket编程
 * @description
 * @author: matilda
 * @create: 2020-06-29 19:29
 **/

/**
 * 把打印日志的代码封装一下
 */
public class Logger {
    public static void debug(String message) {
        System.out.printf("%s: DEBUG: %s%n", new Date(), message);
    }

    public static void error(String message) {
        System.out.printf("%s: %s: ERROR: %s%n", Thread.currentThread().getName(), new Date(), message);
    }
}

