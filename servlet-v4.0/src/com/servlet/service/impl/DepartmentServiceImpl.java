package com.servlet.service.impl;

import com.servlet.dao.DepartmentDao;
import com.servlet.dao.impl.DepartmentDaoImpl;
import com.servlet.pojo.Department;
import com.servlet.service.DepartmentService;
import com.servlet.utils.FactoryUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 类描述
 * @create 2021-09-22 14:29
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;
    // 单例模式，懒汉式
    private static DepartmentServiceImpl departmentService;

    private DepartmentServiceImpl() {
        departmentDao = FactoryUtils.createInstance(DepartmentDaoImpl.class.getName());
    }
    // 单例模式，懒汉式
    public static DepartmentServiceImpl getDepartmentServiceInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentServiceImpl();
        }
        return departmentService;
    }

    @Override
    public List<Department> selectAllDepartment() throws SQLException {
        return departmentDao.selectAllDepartment();
    }

    @Override
    public Department selectOneDepartment(Integer pid) throws SQLException {
        return departmentDao.selectOneDepartment(pid);
    }
}
