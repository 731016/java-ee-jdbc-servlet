package com.servlet.pojo;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: TODO 部门类
 * @create 2021-09-22 13:55
 */
public class Department {
    private Integer pid;
    private String pname;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Department() {
    }

    public Department(Integer pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }
}
