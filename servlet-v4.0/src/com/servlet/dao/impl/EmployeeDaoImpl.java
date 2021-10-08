package com.servlet.dao.impl;

import com.servlet.dao.EmployeeDao;
import com.servlet.pojo.Department;
import com.servlet.pojo.Employee;
import com.servlet.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.SortedMap;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 类描述
 * @create 2021-09-22 14:28
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private QueryRunner queryRunner;
    // 单例模式，懒汉式
    private static EmployeeDaoImpl employeeDao;

    private EmployeeDaoImpl() {
        queryRunner = JdbcUtils.getQueryRunner();
    }
    // 单例模式，懒汉式
    public static EmployeeDaoImpl getEmployeeDaoInstance(){
        if (employeeDao==null){
            employeeDao=new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    @Override
    public Integer addEmployee(Employee emp) throws SQLException {
        String sql = "insert into employee values(default,?,?,?,?,?,?,null)";
        Object[] objects = {
                emp.getEname(),
                emp.getSex(),
                emp.getDepartment().getPid(),
                emp.getSalary(),
                emp.getDizhi(),
                emp.getJob()
        };
        return queryRunner.update(sql, objects);
    }

    @Override
    public List<Employee> selectAllEmployee(Integer index, Integer size) throws SQLException {
        String sql = "select * from employee e inner join department d on e.pid=d.pid group by eid limit ?,?";
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement prstmt = conn.prepareStatement(sql);
        Object[] objects = {
                (index - 1) * size,
                size
        };
        for (int i = 0; i < objects.length; i++) {
            prstmt.setObject(i + 1, objects[i]);
        }
        ResultSet resultSet = prstmt.executeQuery();
        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            employeeList.add(new Employee(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    new Department(resultSet.getInt(9), resultSet.getString(10)),
                    resultSet.getString(8)
            ));
        }
        conn.close();
        return employeeList;
    }

    @Override
    public Integer delEmployee(Integer eid) throws SQLException {
        String sql = "delete from employee where eid=?";
        Object[] objects = {eid};
        int delFlag = queryRunner.update(sql, objects);
        return delFlag;
    }

    @Override
    public List<Employee> selectConditionEmployee(Integer pidEmp, Integer index, Integer size) throws SQLException {
        String sql = "select * from employee e inner join department d on e.pid=d.pid where e.pid=? group by eid limit ?,?";
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement prstmt = conn.prepareStatement(sql);
        Object[] objects = {
                pidEmp,
                (index - 1) * size,
                size
        };
        for (int i = 0; i < objects.length; i++) {
            prstmt.setObject(i + 1, objects[i]);
        }
        ResultSet resultSet = prstmt.executeQuery();
        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            employeeList.add(new Employee(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    new Department(resultSet.getInt(9), resultSet.getString(10)),
                    resultSet.getString(8)
            ));
        }
        conn.close();
        return employeeList;
    }

    @Override
    public Integer conditiondelEmployee(Integer[] eid) throws SQLException {
        String sql = "delete from employee where eid in(";
        for (int i = 0; i < eid.length; i++) {
            sql += eid[i] + ",";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ")";
        int delFlag = queryRunner.update(sql);
        return delFlag;
    }

    @Override
    public Employee selectOneEmployee(Integer eid) throws SQLException {
        String sql = "select * from employee e inner join department d on e.pid=d.pid where e.eid=?";
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement prstmt = conn.prepareStatement(sql);
        prstmt.setObject(1, eid);
        ResultSet resultSet = prstmt.executeQuery();
        Employee employee = null;
        while (resultSet.next()) {
            employee = new Employee(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    new Department(resultSet.getInt(9), resultSet.getString(10)),
                    resultSet.getString(8)
            );
        }
        conn.close();
        return employee;
    }

    @Override
    public Integer editOneEmployee(Integer empid, Employee employee) throws SQLException {
        String sql = "update employee set ename=?,sex=?,pid=?,salary=?,dizhi=?,job=? where eid=" + empid;
        Object[] objects = {
                employee.getEname(),
                employee.getSex(),
                employee.getDepartment().getPid(),
                employee.getSalary(),
                employee.getDizhi(),
                employee.getJob()
        };
        int insertFlag = queryRunner.update(sql, objects);
        return insertFlag;
    }

    @Override
    public Integer addUserImage(Integer eid, String file) throws SQLException {
        String sql = "update employee set file=? where eid=?";
        Object[] objects = {
                file,
                eid
        };
        int updateFlag = queryRunner.update(sql, objects);
        return updateFlag;
    }

    @Override
    public String selectUserImage(Integer eid) throws SQLException {
        String sql = "select file from employee where eid=?";
        Object[] objects = {eid};
        ScalarHandler<String> handler = new ScalarHandler<>();
        String queryFlag = queryRunner.query(sql, handler, objects);
        return queryFlag;
    }

    @Override
    public Integer selectAllCount() throws SQLException {
        String sql = "select count(*) from employee e inner join department d on e.pid=d.pid";
        ScalarHandler<Long> handler = new ScalarHandler();
        Long count = queryRunner.query(sql, handler);
        return Math.toIntExact(count);
    }

    @Override
    public Integer selectConditionAllCount(Integer pid) throws SQLException {
        String sql = "select count(*) from employee e inner join department d on e.pid=d.pid where d.pid=?";
        ScalarHandler<Long> handler = new ScalarHandler();
        Object[] objects = {pid};
        Long depCount = queryRunner.query(sql, handler, objects);
        return Math.toIntExact(depCount);
    }
}
