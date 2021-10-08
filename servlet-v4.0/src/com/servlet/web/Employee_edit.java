package com.servlet.web;

import com.servlet.pojo.Department;
import com.servlet.pojo.Employee;
import com.servlet.service.DepartmentService;
import com.servlet.service.EmployeeService;
import com.servlet.service.impl.DepartmentServiceImpl;
import com.servlet.service.impl.EmployeeServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/*
获取修改后的员工信息
 */
@WebServlet(name = "Employee_edit",urlPatterns = "/employee/employee_edit")
public class Employee_edit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer eid = Integer.parseInt(request.getParameter("eid"));
        EmployeeService service= EmployeeServiceImpl.getEmployeeServiceInstance();
        Employee employee = service.selectOneEmployee(eid);

        DepartmentService departmentService= DepartmentServiceImpl.getDepartmentServiceInstance();
        List<Department> departments = departmentService.selectAllDepartment();
        request.getSession().setAttribute("departmentList",departments);
        request.getSession().setAttribute("employeeOne",employee);
        response.sendRedirect("/employee/employee_edit.jsp");
    }
}
