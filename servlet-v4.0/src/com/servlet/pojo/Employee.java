package com.servlet.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 员工类
 * @create 2021-09-22 13:53
 */
@Data
@NoArgsConstructor
public class Employee {
    private Integer eid;
    private String ename;
    private String sex;
    private Double salary;
    private String dizhi;
    private String job;
    private String file;
    private Department department;

    public Employee(Integer eid, String ename, String sex, Double salary, String dizhi, String job, Department department) {
        this.eid = eid;
        this.ename = ename;
        this.sex = sex;
        this.salary = salary;
        this.dizhi = dizhi;
        this.job = job;
        this.department = department;
    }

    public Employee(Integer eid, String ename, String sex, Double salary, String dizhi, String job, Department department, String file) {
        this.eid = eid;
        this.ename = ename;
        this.sex = sex;
        this.salary = salary;
        this.dizhi = dizhi;
        this.job = job;
        this.department = department;
        this.file = file;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                ", dizhi='" + dizhi + '\'' +
                ", job='" + job + '\'' +
                ", file='" + file + '\'' +
                ", department=" + department +
                '}';
    }
}
