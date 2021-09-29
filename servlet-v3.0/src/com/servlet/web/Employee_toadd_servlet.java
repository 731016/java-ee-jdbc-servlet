package com.servlet.web;

import com.servlet.pojo.Department;
import com.servlet.service.DepartmentService;
import com.servlet.service.impl.DepartmentServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
/*
    在增加员工之间处理

    请求方式：post
    请求地址：/employee/employee_toadd
    请求参数：无

    保存所有部门名称到session，("departmentList", departmentList)[类型：List<Department>]
    重定向：/employee/employee_add.jsp
 */
@WebServlet(name = "Employee_toadd_servlet",urlPatterns = "/employee/employee_toadd")
public class Employee_toadd_servlet extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        DepartmentService service = new DepartmentServiceImpl();
        List<Department> departmentList = service.selectAllDepartment();
        HttpSession session = request.getSession();
        session.setAttribute("departmentList", departmentList);
        response.sendRedirect("/employee/employee_add.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
