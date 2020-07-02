import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @program: HTTP协议
 * @description
 * @author: matilda
 * @create: 2020-07-02 10:16
 **/
public class URLEncodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //编码
        String encode = URLEncoder.encode("C++","utf-8");
        System.out.println(encode);

        String 我 = URLEncoder.encode("我","utf-8");
        System.out.println(我);

        //解码
        String decode = URLDecoder.decode("C%2B%2B","utf-8");
        System.out.println(decode);
    }
}
