package com.servlet.utils;

import com.servlet.pojo.Employee;
import com.servlet.service.EmployeeService;
import com.servlet.service.impl.EmployeeServiceImpl;
import lombok.Data;

import java.util.List;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 分页查询工具类
 * @create 2021-09-27 16:30
 */
@Data
public class PageUtils {
    private EmployeeService service;

    public PageUtils() {
        service = new EmployeeServiceImpl();
    }

    private Integer size;   //每页大小
    private Integer index;  //当前页码
    private Integer pid;


    //计算总人数
    public Integer getRows() {
        Integer rows = 0;
        try {
            rows = (pid == null || pid.equals("")) ? service.selectAllCount() : service.selectConditionAllCount(pid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rows;
    }


    //计算总页数
    public Integer getPages() {
        Integer pages = 0;
        Integer rows = getRows();
        pages = (rows % size == 0) ? rows / size : rows / size + 1;
        return pages;
    }

    // 判断是否存在pid，来决定查询所有员工或者部门员工
    public List<Employee> listCondAll() throws Exception {
        List<Employee> employees = null;
        if (this.pid == null || (this.pid).equals("")) {
            employees = service.selectAllEmployee(this.index, this.size);
        } else {
            employees = service.selectConditionEmployee(this.pid, this.index, this.size);
        }
        return employees;
    }
}
