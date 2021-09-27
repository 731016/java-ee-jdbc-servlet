package com.servlet.service;

import com.servlet.pojo.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    // 增加员工
    public Integer addEmployee(Employee emp) throws SQLException;
    // 查询所有员工 -分页查询
    public List<Employee> selectAllEmployee(Integer index,Integer size) throws SQLException;
    // 根据eid删除员工
    public Integer delEmployee(Integer eid) throws SQLException;
    // 根据eid批量删除员工
    public Integer conditiondelEmployee(Integer[] eid) throws SQLException;
    // 根据pid查询指定的员工 -分页查询
    public List<Employee> selectConditionEmployee(Integer pid,Integer index,Integer size) throws SQLException;
    // 根据eid查询指定的员工
    public Employee selectOneEmployee(Integer eid) throws SQLException;
    // 根据eid修改员工信息
    public Integer editOneEmployee(Integer eid,Employee employee) throws SQLException;
    // 根据eid新增用户图片
    public Integer addUserImage(Integer eid,String file) throws SQLException;
    // 根据eid查询用户图片
    public String selectUserImage(Integer eid) throws SQLException;
    // 查询所有员工总人数
    public Integer selectAllCount() throws SQLException;
    // 查询单个部门所有员工人数
    public Integer selectConditionAllCount(Integer pid) throws SQLException;
}
