package com.servlet.pojo;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 员工类
 * @create 2021-09-22 13:53
 */
public class Employee {
    private Integer eid;
    private String ename;
    private String sex;
    private Double salary;
    private String dizhi;
    private String job;
    private String file;
    private Department department;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

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
