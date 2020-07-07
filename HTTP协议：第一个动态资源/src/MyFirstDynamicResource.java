import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: 第一个动态资源
 * @description
 * @author: matilda
 * @create: 2020-07-05 09:39
 **/

public class MyFirstDynamicResource extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //1.通过query string，取到wd这个name所对应的value
        String wd = req.getParameter("wd");

        String name = req.getParameter("name");
        String age = req.getParameter("age");

        //2.返回的Content-Type是text/html;字符集编码是utf-8
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        //3.输出响应的内容
        writer.println("<h1>我是 /s 资源</h1>");
        writer.println("<p>用户要查找的关键字是："+wd+"</p>");
    }
}
