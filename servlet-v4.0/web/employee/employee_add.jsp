<%@ page import="com.servlet.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/employee/employee_add" method="post">
    <div>
        <label>姓名：</label>
        <input type="text" name="ename" placeholder="请输入中文，数字，英文...">
    </div>


    <div>
        <label>性别：</label>
        <input type="radio" name="sex" value="男" checked>男
        <input type="radio" name="sex" value="女">女
    </div>

    <div>
        <label>员工部门：</label>
        <select name="pid">
            <c:forEach items="${sessionScope.departmentList}" var="department">
                <option value="${department.getPid()}">${department.getPname()}</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <label>工资：</label>
        <input type="number" name="salary" placeholder="请输入数字...">
    </div>

    <div>
        <label>地址：</label>
        <input type="text" name="dizhi" placeholder="请输入地址...">
    </div>

    <div>
        <label>职位：</label>
        <select name="job">
            <option value="产品经理">产品经理</option>
            <option value="开发工程师">开发工程师</option>
            <option value="实施工程师">实施工程师</option>
            <option value="项目主管">项目主管</option>
            <option value="会计">会计</option>
            <option value="运维工程师">运维工程师</option>
        </select>
    </div>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>
</body>
</html>
