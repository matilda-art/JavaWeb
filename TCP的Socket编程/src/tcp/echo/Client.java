package tcp.echo;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * @program: TCP的Socket编程
 * @description
 * 客户端
 *
 * 第一个客户端连接上之后，不输入任何请求。后边的客户端连接上之后，正常输入请求，看是什么结果？
 * 第二个客户端发送了请求之后无法收到响应。
 *
 * 因为Server正在等第一个客户端的请求，而由于第一个客户端没有发送任何请求。
 * 所以，Server其他事情都干不了了。（处理第二个客户端）
 * 如何解决？
 * 引入多线程（1.提升速度 2.堵塞场景）
 * 直接使用线程池进行处理。主线程负责accept();  处理通话的任务交给线程池的线程去处理。
 *
 * @author: matilda
 * @create: 2020-06-30 10:57
 **/

public class Client {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = Server.SERVER_PORT;

    public static void main(String[] args) throws IOException {
        Scanner stdinScanner = new Scanner(System.in);

        Logger.debug("准备连接服务端(" + SERVER_HOST + ":" + SERVER_PORT + ")");
        try(Socket socket = new Socket(SERVER_HOST,SERVER_PORT)){
            Logger.debug("连接已建立");
            SocketAddress localSocketAddress = socket.getLocalSocketAddress();
            Logger.debug("连接中，本地地址是：" + localSocketAddress);
            int localPort = socket.getLocalPort();
            Logger.debug("连接中，本地端口是：" + localPort);
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            Logger.debug("连接中，对方地址是：" + remoteSocketAddress);
            int port = socket.getPort();
            Logger.debug("连接中，对方端口是：" + port);

            System.out.println("请填写请求>");
            String request = stdinScanner.nextLine();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(inputStream,"UTF-8");
            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream,"UTF-8")
            );

            Logger.debug("准备发送请求：" + request);
            writer.printf("%s\r\n",request);
            Logger.debug("发送调用成功，记得flush");
            writer.flush();
            Logger.debug("请求发送成功");

            Logger.debug("准备接受响应：");
            String response = scanner.nextLine();
            Logger.debug("接收到的响应是：" + response);
            System.out.println(response);
        }
    }
}
