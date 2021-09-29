package com.servlet.web;

import com.servlet.dao.DepartmentDao;
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
/*
    增加员工

    请求方式：post
    请求地址：/employee/employee_add
    请求参数：
        ename 员工姓名
        sex 性别
        pid 部门id
        salary 工资
        dizhi 地址
        job 职位

        重定向：/employee/employee_select
 */
@WebServlet(name = "Employee_add_servlet", urlPatterns = "/employee/employee_add")
public class Employee_add_servlet extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String ename = request.getParameter("ename");
        String sex = request.getParameterValues("sex")[0];
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        Double salary = Double.parseDouble(request.getParameter("salary"));
        String dizhi = request.getParameter("dizhi");
        String job = request.getParameter("job");

//        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = new Department(pid, "");

        Employee employee = new Employee(null, ename, sex, salary, dizhi, job, department);

        EmployeeService service = new EmployeeServiceImpl();
        Integer addFlag = service.addEmployee(employee);
        if (addFlag > 0) {
            System.out.println("添加成功！");
            response.sendRedirect("/employee/employee_select");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
