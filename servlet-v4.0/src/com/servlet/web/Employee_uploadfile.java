package com.servlet.web;

import com.servlet.service.EmployeeService;
import com.servlet.service.impl.EmployeeServiceImpl;
import lombok.SneakyThrows;

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

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String eidFile = request.getParameter("eidFile");
        request.getSession().setAttribute("eid", eidFile);

        //根据eid获取数据库的存在的图像
        EmployeeService service =  EmployeeServiceImpl.getEmployeeServiceInstance();
        String originFile = service.selectUserImage(Integer.parseInt(eidFile));
        request.getSession().setAttribute("originFile",originFile);

        response.sendRedirect("/index.jsp");
    }
}
