package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Controller {
    private static final String SERVER_HOST = "49.233.172.121";
    private static final int SERVER_PORT = 8260;

    private DatagramSocket clientSocket = null;

    {
        try {
            clientSocket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    TextField input;

    @FXML
    Text output;

    @FXML public void handleSubmit(ActionEvent e) {
        if (clientSocket == null) {
            return;
        }

        // 1. 从输入框中读取用户的输入
        String request = input.getText().trim();
        System.out.printf("%s: DEBUG: 读取用户的输入是: %s%n", new Date(), request);

        try {
            byte[] sendBuffer = request.getBytes("UTF-8");
            // 绑定对方的 ip
            // 绑定对方的 port
            // 127.0.0.1:8260
            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer, sendBuffer.length,
                    InetAddress.getByName(SERVER_HOST),
                    SERVER_PORT);

            System.out.printf("%s: DEBUG: 准备发送请求 ...%n", new Date());
            clientSocket.send(sendPacket);
            System.out.printf("%s: DEBUG: 请求发送成功%n", new Date());


            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, 0, receiveBuffer.length);

            System.out.printf("%s: DEBUG: 准备接收响应 ...%n", new Date());
            clientSocket.receive(receivePacket);
            System.out.printf("%s: DEBUG: 接收响应成功%n", new Date());

            // 转成字符形式
            String response = new String(receiveBuffer, 0, receivePacket.getLength(), "UTF-8");
            System.out.printf("%s: DEBUG: 接收到的响应是: %s%n", new Date(), response);

            // 输出到图形化界面上
            output.setText(response);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
