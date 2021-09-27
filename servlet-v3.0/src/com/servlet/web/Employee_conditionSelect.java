package com.servlet.web;

import com.servlet.pojo.Employee;
import com.servlet.service.DepartmentService;
import com.servlet.service.EmployeeService;
import com.servlet.service.impl.DepartmentServiceImpl;
import com.servlet.service.impl.EmployeeServiceImpl;
import com.servlet.utils.PageUtils;
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
//    public static final Integer PAGESIZE = 8; // 每页的数据条数

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 部门点击分页，不传递查询的form表单

        PageUtils pageUtils = new PageUtils();
        pageUtils.setSize(8);


        Integer pid = null;
        if (request.getParameter("pidSelect") == null) {
            pid = (Integer) request.getSession().getAttribute("depid");
        } else {
            pid = Integer.parseInt(request.getParameter("pidSelect"));
        }
        pageUtils.setPid(pid); //设置部门id

        EmployeeService service = new EmployeeServiceImpl();
//        Integer count = service.selectConditionAllCount(pid);
//        Integer pages = (count % PAGESIZE == 0) ? count / PAGESIZE : (count / PAGESIZE) + 1;

        Integer pages = pageUtils.getPages();
        if (pid == -1) {
            response.sendRedirect("/employee/employee_select");
            return;
        } else {
            Integer pageIndex = 0; // 当前页码
            if (request.getParameter("i") == null || "".equals(request.getParameter("i"))) {
                pageIndex = 1;
            } else {
                pageIndex = Integer.valueOf(request.getParameter("i"));
                if (pageIndex > pages) {
                    pageIndex = pages;
                }
            }
            pageUtils.setIndex(pageIndex); //设置当前页码

//            List<Employee> employeeCondList = service.selectConditionEmployee(pid, pageIndex, PAGESIZE);
            List<Employee> employeeCondList = pageUtils.listCondAll();

//            request.getSession().setAttribute("pages", pages);
//            request.getSession().setAttribute("i", pageIndex);
            request.getSession().setAttribute("pages",pageUtils.getPages());
            request.getSession().setAttribute("i",pageUtils.getIndex());

            request.getSession().setAttribute("employeeList", employeeCondList);
            //查询单个部门，设置部门编号的session
            request.getSession().setAttribute("depid", employeeCondList.get(0).getDepartment().getPid());

            response.sendRedirect("/employee/employee_select.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}