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
批量删除员工
 */
@WebServlet(name = "Employee_conditionDel", urlPatterns = "/employee/employee_conditionDel")
public class Employee_conditionDel extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] eids = request.getParameterValues("eid");
        EmployeeService service = new EmployeeServiceImpl();
        Integer[] eidInt = new Integer[eids.length];
        for (int i = 0; i < eids.length; i++) {
            eidInt[i] = Integer.valueOf(eids[i]);
        }
        Integer delFalg = service.conditiondelEmployee(eidInt);
        System.out.println("删除行数：" + delFalg);
        response.sendRedirect("/employee/employee_select");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
