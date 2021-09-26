package com.servlet.dao;

import com.servlet.pojo.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    // 增加员工
    public Integer addEmployee(Employee emp) throws SQLException;
    // 查询所有员工
    public List<Employee> selectAllEmployee() throws SQLException;
    // 根据eid删除员工
    public Integer delEmployee(Integer eid) throws SQLException;
    // 根据eid批量删除员工
    public Integer conditiondelEmployee(Integer[] eid) throws SQLException;
    // 根据pid查询指定的员工
    public List<Employee> selectConditionEmployee(Integer pid) throws SQLException;
    // 根据eid查询指定的员工
    public Employee selectOneEmployee(Integer eid) throws SQLException;
    // 根据eid修改员工信息
    public Integer editOneEmployee(Integer eid,Employee employee) throws SQLException;
    // 根据eid新增用户图片
    public Integer addUserImage(Integer eid,String file) throws SQLException;
}
