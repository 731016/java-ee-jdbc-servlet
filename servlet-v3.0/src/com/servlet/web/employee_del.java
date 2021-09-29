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
    删除员工

    请求方式：get
    请求地址：/employee_del
    请求参数：eid=员工id

    重定向：/employee/employee_select
 */
@WebServlet(name = "employee_del", urlPatterns = "/employee_del")
public class employee_del extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        Integer eid = Integer.parseInt(request.getParameter("eid"));
        EmployeeService service=new EmployeeServiceImpl();
        service.delEmployee(eid);
        response.sendRedirect("/employee/employee_select");
    }
}
