package com.servlet.dao;

import com.servlet.pojo.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {
    // 获取所有部门信息
    public List<Department> selectAllDepartment() throws SQLException;
    // 根据pid获取对应的部门信息
    public Department selectOneDepartment(Integer pid) throws SQLException;
}
