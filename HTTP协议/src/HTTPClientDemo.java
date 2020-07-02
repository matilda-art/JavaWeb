import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @program: HTTP协议
 * @description
 * @author: matilda
 * @create: 2020-07-02 10:29
 **/
//使用socket编程发送一个HTTP协议
public class HTTPClientDemo {
    public static void main(String[] args) {
        //URL:http://www.baidu.com/s?wd=java
        String SERVER_HOST = "www.baidu.com";
        int SERVER_PORT = 80;

        try(Socket socket = new Socket(SERVER_HOST,SERVER_PORT)){
            //GET方法的请求，不包含请求体
            String request = "GET /s?wd=java HTTP/1.0\r\nHOST: www.baidu.com\r\n\r\n";
            //成功的发送请求
            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream(),"utf-8")
            );
            writer.print(request);
            writer.flush();

            //等待响应
            Scanner scanner = new Scanner(socket.getInputStream(),"utf-8");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
