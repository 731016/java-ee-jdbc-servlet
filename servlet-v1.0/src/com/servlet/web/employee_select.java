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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
/*
查询员工
 */
@WebServlet(name = "employee_select", urlPatterns = "/employee/employee_select")
public class employee_select extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        EmployeeService service = new EmployeeServiceImpl();
        DepartmentService departmentService=new DepartmentServiceImpl();

        List<Employee> employeeList = service.selectAllEmployee();
        List<Department> departmentList = departmentService.selectAllDepartment();
        HttpSession session = request.getSession();

        session.setAttribute("employeeList", employeeList);
        session.setAttribute("departmentList",departmentList);

        response.sendRedirect("/employee/employee_select.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
