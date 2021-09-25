package com.servlet.web;

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
import java.util.Enumeration;
import java.util.List;
/*
选择部门查询
 */
@WebServlet(name = "Employee_conditionSelect", urlPatterns = "/employee/employee_conditionSelect")
public class Employee_conditionSelect extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer pid = Integer.parseInt(request.getParameter("pidSelect"));
        if (pid == -1) {
            response.sendRedirect("/employee/employee_select");
            return;
        }else {
            EmployeeService service = new EmployeeServiceImpl();
            List<Employee> employeeCondList = service.selectConditionEmployee(pid);
            request.getSession().setAttribute("employeeList", employeeCondList);
            response.sendRedirect("/employee/employee_select.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
