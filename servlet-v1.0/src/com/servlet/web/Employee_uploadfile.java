package com.servlet.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
    获取要添加头像的员工id，设置session
 */
@WebServlet(name = "Employee_uploadfile",urlPatterns = "/uploadfile")
public class Employee_uploadfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String eidFile = request.getParameter("eidFile");
        request.getSession().setAttribute("eid", eidFile);
        response.sendRedirect("/index.jsp");
    }
}
