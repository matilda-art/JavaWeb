package com.matilda;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: 通过Servlet学习HTTP
 * @description
 * @author: matilda
 * @create: 2020-07-09 21:02
 **/

@WebServlet("/set-cookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("set-Cookie","today=2020-07-09");

        resp.setContentType("text/plain; charset=utf-8");
        resp.getWriter().println("种下Cookie：today=2020-07-09");
    }
}
