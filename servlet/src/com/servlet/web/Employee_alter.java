package com.servlet.web;

import com.servlet.pojo.Department;
import com.servlet.pojo.Employee;
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
修改员工信息，不包含头像，提交到数据库
 */
@WebServlet(name = "Employee_alter",urlPatterns = "/employee/employee_alter")
public class Employee_alter extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer eid = Integer.valueOf(request.getParameter("eid"));
        String ename = request.getParameter("ename");
        String sex = request.getParameterValues("sex")[0];
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        Double salary = Double.valueOf(request.getParameter("salary"));
        String dizhi = request.getParameter("dizhi");
        String job = request.getParameter("job");
        Employee employee = new Employee(null, ename, sex, salary, dizhi, job, new Department(pid, ""));
        EmployeeService service = new EmployeeServiceImpl();
        Integer alterFlag = service.editOneEmployee(eid, employee);
        System.out.println("修改行数：" + alterFlag);
        response.sendRedirect("/employee/employee_edit?eid="+eid);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
