#  :heart: JavaEE+JDBC+Servlet+EL表达式的员工信息管理系统

## 介绍
简易的员工管理系统，包括信息的增删改查，新增员工图像

和上一次系统[bootstrap+JavaSE+JDBC的信息管理系统](https://gitee.com/LovelyHzz/Info-management-system)相比
1. 使用了servlet把业务处理代码放在后台处理，删除了不必要的jsp页面
2. 使用El表达式展示数据，不用在前台获取一遍session数据

## 项目展示效果
<hr>

### 主页  
![主页](https://images.gitee.com/uploads/images/2021/0925/104935_f4123936_8254421.png "主页.png")

### 注册
![注册页面](https://images.gitee.com/uploads/images/2021/0925/104049_736a293e_8254421.png "注册.png")

### 员工列表
![用户列表-第一次注册无须添加头像](https://images.gitee.com/uploads/images/2021/0925/104127_4d423d7c_8254421.png "用户列表-注册无须添加头像.png")
![用户列表-展示工资样式{大于8000：绿色；6000~8000之间：红色}](https://images.gitee.com/uploads/images/2021/0925/104208_ab42ad8c_8254421.png "用户列表-展示工资样式.png")
![员工列表-筛选部门](https://images.gitee.com/uploads/images/2021/0925/110316_d9957fdf_8254421.png "员工列表-筛选部门.png")

### 修改
![修改页面](https://images.gitee.com/uploads/images/2021/0925/104408_5e4c5bc2_8254421.png "修改页面.png")
![修改一下工资](https://images.gitee.com/uploads/images/2021/0925/104433_895255a2_8254421.png "修改一下工资.png")
![修改完成](https://images.gitee.com/uploads/images/2021/0925/104457_9b1987bc_8254421.png "修改完成.png")

### 删除
![删除前](https://images.gitee.com/uploads/images/2021/0925/104538_364aa0ab_8254421.png "删除前.png")
![删除后](https://images.gitee.com/uploads/images/2021/0925/104559_19195d83_8254421.png "删除后.png")
<hr>

![批量删除](https://images.gitee.com/uploads/images/2021/0925/104623_0fb832b8_8254421.png "批量删除.png")
![批量删除完成](https://images.gitee.com/uploads/images/2021/0925/104637_d7c87b0b_8254421.png "批量删除完成.png")

### 添加头像
![双击头像添加头像](https://images.gitee.com/uploads/images/2021/0925/104751_7fbc6baa_8254421.png "双击头像添加头像.png")
![头像修改成功](https://images.gitee.com/uploads/images/2021/0925/104818_14be8fb0_8254421.png "头像修改成功.png")

## 数据库设计
<ins>MySQL数据库<ins>
```text
员工表(员工编号,员工姓名,性别,部门编号,工资,地址,职位,头像文件名称)
部门表(部门编号,部门名称)
```
```sql
create table employee(
	eid int auto_increment primary key,
	ename varchar(20),
	sex varchar(3),
	pid int,
	salary double(12,2),
	dizhi varchar(100),
	job varchar(20)
)default charset=utf8;
insert into employee values(default,'涂鏊飞','男',802,8000,'武汉市','销售经理',null);
insert into employee values(default,'胡梓卓','男',701,10000,'天门市','项目经理',null);
insert into employee values(default,'李小龙','男',702,9000,'厦门市','开发人员',null);
insert into employee values(default,'董文武','男',702,8500,'黑龙江','开发人员',null);

create table department(
	pid int primary key,
	pname varchar(50)
)default charset=utf8;
insert into department values(701,'研发部');
insert into department values(702,'市场部');
insert into department values(802,'销售部');

alter table employee add constraint fk_pid foreign key(pid) references department(pid);
#on delete cascade on update cascade;

select * from employee;
select * from department;
```

## 项目结构
![项目结构-后台部分](https://images.gitee.com/uploads/images/2021/0925/110020_0d22cea6_8254421.png "项目结构-后台部分.png")
![web前端部分](https://images.gitee.com/uploads/images/2021/0925/105949_4767b4e5_8254421.png "web前端部分.png")

## 主要功能
1. 员工注册
2. 员工列表展示-可筛选部门
3. 删除单个员工，批量删除员工
4. 修改员工信息
5. 双击头像，可提交员工头像

 **刷新用户列表无信息，可点击查询所有部门信息** 

## db.properties
```java
#通过反射获取获取包
#driverClassName=com.mysql.jc.jdbc.Driver #8.0
driverClassName=com.mysql.jdbc.Driver #5.0 数据库版本
# localhost 本地服务器
# 3306 端口
# demo 数据库名
url=jdbc:mysql://localhost:3306/demo?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
# 用户名
username=root
# 密码
password=123456
```

## utils工具类
```java
package com.servlet.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: 数据库操作工具类 类描述
 * @create 2021-09-01 17:18
 */
public class JdbcUtils {
    private static DataSource ds;
    static {
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            //2.获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        }
        catch (Exception e){
            ds = null;
        }
    }

    public static Connection getConnection(){
        Connection con;
        try {
            con = ds.getConnection();
        }
        catch (Exception ex){
            con = null;
        }
        return con;
    }
    /* 获取 QueryRunner */
    public static QueryRunner getQueryRunner(){
        QueryRunner runner = new QueryRunner(ds);
        return runner;
    }
}

```

## pojo数据库映射类
员工头像是新增的功能，Employee类构造方法有两个重载，一个包含file【用来新增图片】，一个不包含【用来插入图片】<br>
员工表：[Employee.java](http://)<br>
部门表：[Department.java](http://)

## dao数据访问层
 **Employee接口** `public interface EmployeeDao`<br>
 :one: public  **Integer** `addEmployee(Employee emp)` throws SQLException; // 增加员工 <br>
 :two: public  **List<Employee>**  `selectAllEmployee()` throws SQLException;// 查询所有员工 <br>
 :three: public  **Integer**  `delEmployee(Integer eid)` throws SQLException;// 根据eid删除员工 <br>
 :four: public  **Integer** `conditiondelEmployee(Integer[] eid)` throws SQLException;// 根据eid批量删除员工 <br>
 :five: public  **List<Employee>**  `selectConditionEmployee(Integer pid)` throws SQLException;// 根据pid查询指定的员工 <br>
 :six: public  **Employee**  `selectOneEmployee(Integer eid)` throws SQLException;// 根据eid查询指定的员工 <br>
 :seven: public  **Integer**  `editOneEmployee(Integer eid,Employee employee)` throws SQLException;// 根据eid修改员工信息 <br>
 :eight: public  **Integer**  `addUserImage(Integer eid,String file)` throws SQLException;// 根据eid新增用户图片 <br>

 **Department接口** `public interface DepartmentDao` <br>
 :one: public  **List<Department>**   **selectAllDepartment()**  throws SQLException;// 获取所有部门信息 <br>
 :two: public  **Department**   **selectOneDepartment(Integer pid)**  throws SQLException;// 根据pid获取对应的部门信息 <br>

## service业务逻辑层
 **EmployeeService接口** 
```java
public interface EmployeeService {
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
```
 **DepartmentService接口** 
```java
public interface DepartmentService {
    // 获取所有部门信息
    public List<Department> selectAllDepartment() throws SQLException;
    // 根据pid获取对应的部门信息
    public Department selectOneDepartment(Integer pid) throws SQLException;
}    
```

## web servlet类，处理请求
 :one: 增加员工 [Employee_add_servlet.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/Employee_add_servlet.java) <br>
 :two: 查询员工 [employee_select.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/employee_select.java)<br>
 :three: 修改员工信息，不包含头像，提交到数据库 [Employee_alter.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/Employee_alter.java)<br>
 :four: 批量删除员工 [Employee_conditionDel.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/Employee_conditionDel.java)<br>
 :five: 选择部门查询 [Employee_conditionSelect.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/Employee_conditionSelect.java)<br>
 :six: 删除员工 [employee_del.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/employee_del.java)<br>
 :seven: 获取修改后的员工信息 [Employee_edit.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/Employee_edit.java)<br>
 :eight: 在增加员工之间处理 [Employee_toadd_servlet.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/Employee_toadd_servlet.java)<br>
 :nine: 获取要添加头像的员工id，设置session [Employee_uploadfile.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/Employee_uploadfile.java)<br>
 :keycap_ten: 处理图片名称，上传到服务器和数据库，删除原文件 [FileServlet.java](https://gitee.com/LovelyHzz/java-ee-jdbc-servlet/blob/master/servlet/src/com/servlet/web/FileServlet.java)<br>
 11. 测试EL表达式 [elSvl.java](http://)

## web目录
![输入图片说明](https://images.gitee.com/uploads/images/2021/0925/114628_cdfb84ea_8254421.png "web.png")