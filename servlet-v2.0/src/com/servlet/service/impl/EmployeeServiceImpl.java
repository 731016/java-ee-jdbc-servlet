package com.servlet.service.impl;

import com.servlet.dao.EmployeeDao;
import com.servlet.dao.impl.EmployeeDaoImpl;
import com.servlet.pojo.Employee;
import com.servlet.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 类描述
 * @create 2021-09-22 15:47
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public Integer addEmployee(Employee emp) throws SQLException {
        return employeeDao.addEmployee(emp);
    }

    @Override
    public List<Employee> selectAllEmployee() throws SQLException {
        return employeeDao.selectAllEmployee();
    }

    @Override
    public Integer delEmployee(Integer eid) throws SQLException {
        return employeeDao.delEmployee(eid);
    }

    @Override
    public List<Employee> selectConditionEmployee(Integer pid) throws SQLException {
        return employeeDao.selectConditionEmployee(pid);
    }

    @Override
    public Integer conditiondelEmployee(Integer[] eid) throws SQLException {
        return employeeDao.conditiondelEmployee(eid);
    }

    @Override
    public Employee selectOneEmployee(Integer eid) throws SQLException {
        return employeeDao.selectOneEmployee(eid);
    }

    @Override
    public Integer editOneEmployee(Integer eid, Employee employee) throws SQLException {
        return employeeDao.editOneEmployee(eid, employee);
    }

    @Override
    public Integer addUserImage(Integer eid, String file) throws SQLException {
        return employeeDao.addUserImage(eid, file);
    }

    @Override
    public String selectUserImage(Integer eid) throws SQLException {
        return employeeDao.selectUserImage(eid);
    }
}
