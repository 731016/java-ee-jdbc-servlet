package com.servlet.dao.impl;

import com.servlet.dao.DepartmentDao;
import com.servlet.pojo.Department;
import com.servlet.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 类描述
 * @create 2021-09-22 14:07
 */
public class DepartmentDaoImpl implements DepartmentDao {
    private QueryRunner queryRunner;

    public DepartmentDaoImpl() {
        queryRunner = JdbcUtils.getQueryRunner();
    }

    @Override
    public List<Department> selectAllDepartment() throws SQLException {
        String sql = "select * from department";
        BeanListHandler<Department> handler = new BeanListHandler(Department.class);
        List<Department> query = queryRunner.query(sql, handler);
        return query;
    }

    @Override
    public Department selectOneDepartment(Integer pid) throws SQLException {
        String sql = "select * from department where pid=?";
        BeanHandler<Department> handler = new BeanHandler<>(Department.class);
        Object[] objects = {pid};
        Department query = queryRunner.query(sql, handler, objects);
        return query;
    }
}
