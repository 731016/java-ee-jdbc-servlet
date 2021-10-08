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
 */
@WebServlet(name = "Employee_toadd_servlet",urlPatterns = "/employee/employee_toadd")
public class Employee_toadd_servlet extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        DepartmentService service =  DepartmentServiceImpl.getDepartmentServiceInstance();
        List<Department> departmentList = service.selectAllDepartment();
        HttpSession session = request.getSession();
        session.setAttribute("departmentList", departmentList);
        response.sendRedirect("/employee/employee_add.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
