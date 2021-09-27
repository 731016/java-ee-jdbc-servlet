package com.servlet.web;

import com.servlet.pojo.Department;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
查询员工
 */
@WebServlet(name = "employee_select", urlPatterns = "/employee/employee_select")
public class employee_select extends HttpServlet {
//    public static final Integer PAGESIZE = 8; // 每页的数据条数

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //查询所有部门清除单个部门信息
        request.getSession().removeAttribute("depid");

        PageUtils pageUtils = new PageUtils();
        pageUtils.setSize(8); // 设置每页大小

        // 设置pid,部门编号
        if (!(request.getSession().getAttribute("depid") == null)) {
            pageUtils.setPid(Integer.parseInt(request.getSession().getAttribute("depid") + ""));
        }


        EmployeeService service = new EmployeeServiceImpl();

        // 获取学生总人数
//        Integer count = service.selectAllCount();
//        Integer pages = (count % PAGESIZE == 0) ? count / PAGESIZE : (count / PAGESIZE) + 1;
        Integer pages = pageUtils.getPages();
        Integer pageIndex = 0; // 当前页码
        if (request.getParameter("i") == null || "".equals(request.getParameter("i"))) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.valueOf(request.getParameter("i"));
            if (pageIndex > pages) {
                pageIndex = pages;
            }
        }
        pageUtils.setIndex(pageIndex); // 设置当前页码
        DepartmentService departmentService = new DepartmentServiceImpl();

//        List<Employee> employeeList = service.selectAllEmployee(pageIndex, PAGESIZE);
        List<Employee> employeeList = pageUtils.listCondAll();

        List<Department> departmentList = departmentService.selectAllDepartment();
        HttpSession session = request.getSession();

//        session.setAttribute("pages", pages); // 总页数
//        session.setAttribute("i", pageIndex); // 当前页码
        session.setAttribute("pages", pageUtils.getPages());
        session.setAttribute("i", pageUtils.getIndex());

        session.setAttribute("employeeList", employeeList);
        session.setAttribute("departmentList", departmentList);

        response.sendRedirect("/employee/employee_select.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
